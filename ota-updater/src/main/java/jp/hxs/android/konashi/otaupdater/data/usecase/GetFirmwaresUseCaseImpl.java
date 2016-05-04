package jp.hxs.android.konashi.otaupdater.data.usecase;

import android.util.Log;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.repository.FirmwaresRepository;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.domain.usecase.GetFirmwaresUseCase;
import rx.Completable;

/**
 * Created by izumin on 5/3/2016 AD.
 */
class GetFirmwaresUseCaseImpl implements GetFirmwaresUseCase {
    public static final String TAG = GetFirmwaresUseCaseImpl.class.getSimpleName();

    private final KonashiOtaUpdaterStore store;
    private final FirmwaresRepository repo;

    @Inject
    GetFirmwaresUseCaseImpl(KonashiOtaUpdaterStore store, FirmwaresRepository repo) {
        this.store = store;
        this.repo = repo;
    }

    @Override
    public Completable execute() {
        return repo.getAll()
                .doOnSuccess(firmwares -> Log.d(TAG, String.valueOf(firmwares.size())))
                .doOnSuccess(store::update)
                .toObservable().toCompletable();
    }
}
