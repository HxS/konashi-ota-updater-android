package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.izumin.android.rxblescanner.RxBleScanner;
import jp.hxs.android.konashi.otaupdater.data.entity.mapper.ScanResultToDeviceMapper;
import jp.hxs.android.konashi.otaupdater.infrastructure.cache.BluetoothDeviceCache;
import jp.hxs.android.konashi.otaupdater.infrastructure.cache.ConnectionCache;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class DevicesDataSourceFactory {
    public static final String TAG = DevicesDataSourceFactory.class.getSimpleName();

    private final RxBleScanner scanner;
    private final ScanResultToDeviceMapper scanResultToDeviceMapper;

    private final BluetoothDeviceCache bluetoothDeviceCache;
    private final ConnectionCache connectionCache;

    @Inject
    public DevicesDataSourceFactory(RxBleScanner scanner,
                                    ScanResultToDeviceMapper scanResultToDeviceMapper,
                                    BluetoothDeviceCache bluetoothDeviceCache,
                                    ConnectionCache connectionCache) {
        this.scanner = scanner;
        this.scanResultToDeviceMapper = scanResultToDeviceMapper;
        this.bluetoothDeviceCache = bluetoothDeviceCache;
        this.connectionCache = connectionCache;
    }

    public DevicesDataSource createRealDataSource() {
        return new DevicesRealDataSource(scanner, scanResultToDeviceMapper, bluetoothDeviceCache, connectionCache);
    }
}
