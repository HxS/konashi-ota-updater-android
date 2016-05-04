package jp.hxs.android.konashi.otaupdater.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.izumin.android.rxblescanner.ScanResultJB;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class ScanResultToDeviceMapper {
    public static final String TAG = ScanResultToDeviceMapper.class.getSimpleName();

    @Inject
    public ScanResultToDeviceMapper() {
    }

    public Device transform(ScanResultJB scanResultJB) {
        Device device = null;

        if (scanResultJB != null) {
            device = new Device(
                    scanResultJB.getDevice().getName(),
                    scanResultJB.getDevice().getAddress()
            );
        }

        return device;
    }
}
