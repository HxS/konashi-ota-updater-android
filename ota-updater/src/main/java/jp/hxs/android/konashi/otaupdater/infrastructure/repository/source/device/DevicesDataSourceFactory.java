package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.izumin.android.rxblescanner.RxBleScanner;
import jp.hxs.android.konashi.otaupdater.data.entity.mapper.ScanResultToDeviceMapper;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class DevicesDataSourceFactory {
    public static final String TAG = DevicesDataSourceFactory.class.getSimpleName();

    private final RxBleScanner scanner;
    private final ScanResultToDeviceMapper scanResultToDeviceMapper;

    @Inject
    public DevicesDataSourceFactory(RxBleScanner scanner, ScanResultToDeviceMapper scanResultToDeviceMapper) {
        this.scanner = scanner;
        this.scanResultToDeviceMapper = scanResultToDeviceMapper;
    }

    public DevicesDataSource createRealDataSource() {
        return new DevicesRealDataSource(scanner, scanResultToDeviceMapper);
    }
}
