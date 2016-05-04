package jp.hxs.android.konashi.otaupdater.data.usecase;

import dagger.Module;
import dagger.Provides;
import jp.hxs.android.konashi.otaupdater.domain.usecase.ConnectDeviceUseCase;
import jp.hxs.android.konashi.otaupdater.domain.usecase.GetFirmwaresUseCase;
import jp.hxs.android.konashi.otaupdater.domain.usecase.ScanDevicesUseCase;
import jp.hxs.android.konashi.otaupdater.domain.usecase.UploadFirmwareToKonashiUseCase;

/**
 * Created by izumin on 5/3/2016 AD.
 */
@Module
public class UseCaseModule {
    public static final String TAG = UseCaseModule.class.getSimpleName();

    @Provides
    GetFirmwaresUseCase getFirmwaresUseCase(GetFirmwaresUseCaseImpl useCase) {
        return useCase;
    }

    @Provides
    ScanDevicesUseCase scanDevicesUseCase(ScanDevicesUseCaseImpl useCase) {
        return useCase;
    }

    @Provides
    ConnectDeviceUseCase connectDeviceUseCase(ConnectDeviceUseCaseImpl useCase) {
        return useCase;
    }

    @Provides
    UploadFirmwareToKonashiUseCase uploadFirmwareToKonashiUseCase(UploadFirmwareToKonashiUseCaseImpl useCase) {
        return useCase;
    }
}
