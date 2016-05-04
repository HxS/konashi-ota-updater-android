package jp.hxs.android.konashi.otaupdater.data.usecase;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.repository.DevicesRepository;
import jp.hxs.android.konashi.otaupdater.domain.repository.FirmwaresRepository;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.domain.usecase.UploadFirmwareToKonashiUseCase;
import rx.Observable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class UploadFirmwareToKonashiUseCaseImpl implements UploadFirmwareToKonashiUseCase {
    public static final String TAG = UploadFirmwareToKonashiUseCaseImpl.class.getSimpleName();

    private final KonashiOtaUpdaterStore store;

    private final FirmwaresRepository firmwaresRepo;
    private final DevicesRepository devicesRepo;

    @Inject
    UploadFirmwareToKonashiUseCaseImpl(KonashiOtaUpdaterStore store,
                                       FirmwaresRepository firmwaresRepo,
                                       DevicesRepository devicesRepo) {
        this.store = store;
        this.firmwaresRepo = firmwaresRepo;
        this.devicesRepo = devicesRepo;
    }

    @Override
    public Observable<Integer> execute() {
        if (store.getFirmware() != null && store.getConnectedDevice() != null) {
            return firmwaresRepo.getBinary(store.getFirmware())
                    .toObservable()
                    .flatMap(binary -> devicesRepo.update(store.getConnectedDevice(), binary));
        } else {
            // TODO: Should throw custom exception
            return Observable.error(new RuntimeException());
        }
    }
}
