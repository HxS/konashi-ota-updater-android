package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import android.support.annotation.Nullable;

import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;

/**
 * Created by izumin on 5/4/2016 AD.
 */
interface KonashiOtaUpdaterView {
    void setFirmware(Firmware firmware);
    void setConnectedDevice(@Nullable ConnectedDevice device);
    void showSelectFirmwareDialog(int requestCode);
    void showConnectDeviceDialog(int requestCode);
}
