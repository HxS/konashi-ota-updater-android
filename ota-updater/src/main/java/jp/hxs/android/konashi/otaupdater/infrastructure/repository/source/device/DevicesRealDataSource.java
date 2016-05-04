package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device;

import android.bluetooth.BluetoothGattCharacteristic;

import info.izumin.android.rxblescanner.RxBleScanner;
import jp.hxs.android.konashi.otaupdater.data.entity.mapper.ScanResultToDeviceMapper;
import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.entity.GattCharacteristic;
import jp.hxs.android.konashi.otaupdater.infrastructure.cache.BluetoothDeviceCache;
import jp.hxs.android.konashi.otaupdater.infrastructure.cache.ConnectionCache;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class DevicesRealDataSource implements DevicesDataSource {
    public static final String TAG = DevicesRealDataSource.class.getSimpleName();

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
}
