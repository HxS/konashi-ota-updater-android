package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.domain.usecase.ConnectDeviceUseCase;
import jp.hxs.android.konashi.otaupdater.domain.usecase.GetFirmwaresUseCase;
import jp.hxs.android.konashi.otaupdater.domain.usecase.ScanDevicesUseCase;
import jp.hxs.android.konashi.otaupdater.presentation.fragment.common.Handlers;
import rx.schedulers.Schedulers;

/**
 * Created by izumin on 5/3/2016 AD.
 */
@UpdaterFragmentScope
class KonashiOtaUpdaterHandlers extends Handlers {
    public static final String TAG = KonashiOtaUpdaterHandlers.class.getSimpleName();

    private static final int SELECT_FIRMWARE_REQUEST_CODE = 101;
    private static final int CONNECT_DEVICE_REQUEST_CODE = 102;

    private final KonashiOtaUpdaterView view;
    private final KonashiOtaUpdaterStore store;

    private final GetFirmwaresUseCase getFirmwaresUseCase;
    private final ScanDevicesUseCase scanDevicesUseCase;
    private final ConnectDeviceUseCase connectDeviceUseCase;

    @Inject
    public KonashiOtaUpdaterHandlers(KonashiOtaUpdaterView view,
                                     KonashiOtaUpdaterStore store,
                                     GetFirmwaresUseCase getFirmwaresUseCase,
                                     ScanDevicesUseCase scanDevicesUseCase,
                                     ConnectDeviceUseCase connectDeviceUseCase) {
        this.view = view;
        this.store = store;
        this.getFirmwaresUseCase = getFirmwaresUseCase;
        this.scanDevicesUseCase = scanDevicesUseCase;
        this.connectDeviceUseCase = connectDeviceUseCase;
    }

    void onSelectFirmwareClicked() {
        addSubscription(
                getFirmwaresUseCase.execute().subscribeOn(Schedulers.io()).subscribe()
        );
        view.showSelectFirmwareDialog(SELECT_FIRMWARE_REQUEST_CODE);
    }

    void onFirmwareSelected(Firmware firmware) {
        store.update(firmware);
    }

    void onConnectClicked() {
        addSubscription(
                scanDevicesUseCase.execute().subscribeOn(Schedulers.io()).subscribe()
        );
        view.showConnectDeviceDialog(CONNECT_DEVICE_REQUEST_CODE);
    }

    void onDeviceSelected(Device device) {
        addSubscription(
                connectDeviceUseCase.execute(device).subscribeOn(Schedulers.io()).subscribe()
        );
    }

    void onUpdateClicked() {
    }
}
