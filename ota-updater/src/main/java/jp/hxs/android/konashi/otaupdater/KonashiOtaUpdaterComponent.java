package jp.hxs.android.konashi.otaupdater;

import javax.inject.Singleton;

import dagger.Component;
import jp.hxs.android.konashi.otaupdater.data.store.StoreModule;
import jp.hxs.android.konashi.otaupdater.data.usecase.UseCaseModule;
import jp.hxs.android.konashi.otaupdater.infrastructure.api.ApiModule;
import jp.hxs.android.konashi.otaupdater.infrastructure.repository.RepositoryModule;
import jp.hxs.android.konashi.otaupdater.presentation.dialog.connectdevice.ConnectDeviceComponent;
import jp.hxs.android.konashi.otaupdater.presentation.dialog.connectdevice.ConnectDeviceModule;
import jp.hxs.android.konashi.otaupdater.presentation.dialog.selectfirmware.SelectFirmwareComponent;
import jp.hxs.android.konashi.otaupdater.presentation.dialog.selectfirmware.SelectFirmwareModule;
import jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater.UpdaterFragmentComponent;
import jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater.UpdaterFragmentModule;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
@Component(
        modules = {
                ApiModule.class,
                StoreModule.class,
                RepositoryModule.class,
                UseCaseModule.class,

                KonashiOtaUpdaterModule.class
        }
)
public interface KonashiOtaUpdaterComponent {
    UpdaterFragmentComponent plus(UpdaterFragmentModule module);

    ConnectDeviceComponent  plus(ConnectDeviceModule module);
    SelectFirmwareComponent plus(SelectFirmwareModule module);
}
