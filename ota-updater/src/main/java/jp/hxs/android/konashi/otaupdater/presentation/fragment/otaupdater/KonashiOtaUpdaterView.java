package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;

/**
 * Created by izumin on 5/4/2016 AD.
 */
interface KonashiOtaUpdaterView {
    void setFirmware(Firmware firmware);
    void setKonashi(Device device);
}
