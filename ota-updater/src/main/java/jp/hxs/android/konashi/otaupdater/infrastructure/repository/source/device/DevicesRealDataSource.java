package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device;

import info.izumin.android.rxblescanner.RxBleScanner;
import jp.hxs.android.konashi.otaupdater.data.entity.mapper.ScanResultToDeviceMapper;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import rx.Observable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class DevicesRealDataSource implements DevicesDataSource {
    public static final String TAG = DevicesRealDataSource.class.getSimpleName();

    private final RxBleScanner scanner;
    private final ScanResultToDeviceMapper mapper;

    DevicesRealDataSource(RxBleScanner scanner, ScanResultToDeviceMapper mapper) {
        this.scanner = scanner;
        this.mapper = mapper;
    }

    @Override
    public Observable<Device> scan() {
        // TODO: DO NOT appear konashi devices when filtering by service uuids...
        // return scanner.startScanJB(GattService.KONASHI.getUuid())
        return scanner.startScanJB()
                .filter(result -> result.getDevice().getName() != null)
                .filter(result -> result.getDevice().getName().startsWith("konashi2"))
                .map(mapper::transform);
    }
}
