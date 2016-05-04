package jp.hxs.android.konashi.otaupdater.presentation.dialog.connectdevice;

import dagger.Subcomponent;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@ConnectDeviceScope
@Subcomponent(
        modules = ConnectDeviceModule.class
)
public interface ConnectDeviceComponent {
    void inject(ConnectDeviceDialog dialog);
}
