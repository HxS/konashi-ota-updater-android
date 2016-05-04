package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.domain.usecase.GetFirmwaresUseCase;
import jp.hxs.android.konashi.otaupdater.presentation.fragment.common.Handlers;

/**
 * Created by izumin on 5/3/2016 AD.
 */
@UpdaterFragmentScope
class KonashiOtaUpdaterHandlers extends Handlers {
    public static final String TAG = KonashiOtaUpdaterHandlers.class.getSimpleName();

    private final KonashiOtaUpdaterView view;
    private final KonashiOtaUpdaterStore store;
    private final GetFirmwaresUseCase getFirmwaresUseCase;

    @Inject
    public KonashiOtaUpdaterHandlers(KonashiOtaUpdaterView view,
                                     KonashiOtaUpdaterStore store,
                                     GetFirmwaresUseCase getFirmwaresUseCase) {
        this.view = view;
        this.store = store;
        this.getFirmwaresUseCase = getFirmwaresUseCase;
    }

    void onSelectFirmwareClicked() {
    }

    public void onFirmwareSelected(Firmware firmware) {
        store.update(firmware);
    }

    void onConnectClicked() {
    }

    void onUpdateClicked() {
    }
}
