package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device;

import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import info.izumin.android.bletia.rx.RxBletia;
import info.izumin.android.rxblescanner.RxBleScanner;
import jp.hxs.android.konashi.otaupdater.data.entity.mapper.ScanResultToDeviceMapper;
import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.entity.GattCharacteristic;
import jp.hxs.android.konashi.otaupdater.infrastructure.cache.BluetoothDeviceCache;
import jp.hxs.android.konashi.otaupdater.infrastructure.cache.ConnectionCache;
import jp.hxs.android.konashi.otaupdater.util.Utils;
import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import static jp.hxs.android.konashi.otaupdater.util.Utils.crc32;
import static jp.hxs.android.konashi.otaupdater.util.Utils.int2bytes;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class DevicesRealDataSource implements DevicesDataSource {
    public static final String TAG = DevicesRealDataSource.class.getSimpleName();

    public static final int OTA_PREPARE_DOWNLOAD = 0x01;
    public static final int OTA_DOWNLOAD = 0x02;
    public static final int OTA_VALIDATE_FIRMWARE = 0x03;

    private static final int CHUNK_SIZE = 20;

    private final RxBleScanner scanner;
    private final ScanResultToDeviceMapper mapper;

    private final BluetoothDeviceCache bluetoothDeviceCache;
    private final ConnectionCache connectionCache;

    DevicesRealDataSource(RxBleScanner scanner,
                          ScanResultToDeviceMapper mapper,
                          BluetoothDeviceCache bluetoothDeviceCache,
                          ConnectionCache connectionCache) {
        this.scanner = scanner;
        this.mapper = mapper;
        this.bluetoothDeviceCache = bluetoothDeviceCache;
        this.connectionCache = connectionCache;
    }

    @Override
    public Observable<Device> scan() {
        // TODO: DO NOT appear konashi devices when filtering by service uuids...
        // return scanner.startScanJB(GattService.KONASHI.getUuid())
        return scanner.startScanJB()
                .filter(result -> result.getDevice().getName() != null)
                .filter(result -> result.getDevice().getName().startsWith("konashi2"))
                .doOnNext(bluetoothDeviceCache::put)
                .map(mapper::transform);
    }

    @Override
    public Single<ConnectedDevice> connect(Device device) {
        return connectionCache.put(bluetoothDeviceCache.get(device.address))
                .toObservable()
                .flatMap(rxBletia -> rxBletia.readCharacteristic(GattCharacteristic.SOFTWARE_REVISION.get(rxBletia)))
                .map(BluetoothGattCharacteristic::getValue)
                .map(String::new)
                .map(rev -> new ConnectedDevice(device.name, device.address, rev))
                .toSingle();
    }

    @Override
    public Completable disconnect(Device device) {
        return connectionCache.remove(device.address);
    }

    @Override
    public Observable<Integer> update(Device device, byte[] binary) {
        final Subject<Integer, Integer> progressSubject = PublishSubject.create();
        final RxBletia bletia = connectionCache.get(device.address);

        prepareDownload(bletia)
                .delay(50, TimeUnit.MILLISECONDS)
                .concatWith(download(bletia, binary))
                .delay(50, TimeUnit.MILLISECONDS)
                .andThen(write(bletia, binary))
                .doOnNext(progressSubject::onNext)
                .toCompletable()
                .delay(50, TimeUnit.MILLISECONDS)
                .doOnCompleted(() -> Log.d(TAG, "begin: validating firmware"))
                .concatWith(validateFirmware(bletia, binary))
                .doOnCompleted(() -> Log.d(TAG, "end:validating firmware"))
                .subscribe(progressSubject::onError, progressSubject::onCompleted);

        return progressSubject;
    }

    private Completable prepareDownload(RxBletia bletia) {
        final BluetoothGattCharacteristic characteristic = GattCharacteristic.OTA_CONTROL_POINT.get(bletia);
        characteristic.setValue(new byte[]{ OTA_PREPARE_DOWNLOAD });
        return bletia.writeCharacteristic(characteristic).toCompletable();
    }

    private Completable download(RxBletia bletia, byte[] binary) {
        final BluetoothGattCharacteristic characteristic = GattCharacteristic.OTA_CONTROL_POINT.get(bletia);
        final byte[] length = int2bytes(binary.length);
        characteristic.setValue(new byte[] { OTA_DOWNLOAD, length[0], length[1] });
        return bletia.writeCharacteristic(characteristic).toCompletable();
    }

    private Observable<Integer> write(RxBletia bletia, byte[] binary) {
        final GattCharacteristic gattCharacteristic = GattCharacteristic.OTA_DATA;
        final Subject<Integer, Integer> progressSubject = new SerializedSubject<>(PublishSubject.create());

        final List<byte[]> chunks = Observable.range(0, binary.length)
                .map(i -> binary[i])
                .buffer(CHUNK_SIZE)
                .map(Utils::list2array)
                .toList().toBlocking().first();

        Observable.<byte[]>create(subscriber -> {
                    progressSubject.scan(0, (i, _p) -> i + 1)
                            .filter(i -> i < chunks.size())
                            .map(chunks::get)
                            .subscribe(subscriber);
                })
                .map(chunk -> {
                    final BluetoothGattCharacteristic characteristic = gattCharacteristic.get(bletia);
                    characteristic.setValue(chunk);
                    return characteristic;
                })
                .delay(50, TimeUnit.MILLISECONDS)
                .flatMap(bletia::writeCharacteristic)
                .retry()
                .map(c -> c.getValue().length)
                .subscribe(progressSubject);

        progressSubject.scan(0, (i, j) -> i + j)
                .filter(i -> i >= binary.length)
                .first()
                .subscribe(_i -> progressSubject.onCompleted());

        return progressSubject;
    }

    private Completable validateFirmware(RxBletia bletia, byte[] binary) {
        final BluetoothGattCharacteristic characteristic = GattCharacteristic.OTA_CONTROL_POINT.get(bletia);

        final byte[] crc32bytes = crc32(binary);
        final byte[] bytes = new byte[1 + crc32bytes.length];
        bytes[0] = OTA_VALIDATE_FIRMWARE;
        System.arraycopy(crc32bytes, 0, bytes, 1, crc32bytes.length);

        characteristic.setValue(bytes);
        return bletia.writeCharacteristic(characteristic).toCompletable();
    }
}
