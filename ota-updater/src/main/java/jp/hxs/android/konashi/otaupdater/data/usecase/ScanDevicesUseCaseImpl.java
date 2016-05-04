package jp.hxs.android.konashi.otaupdater.data.usecase;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.repository.DevicesRepository;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.domain.usecase.ScanDevicesUseCase;
import rx.Completable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class ScanDevicesUseCaseImpl implements ScanDevicesUseCase {
    public static final String TAG = ScanDevicesUseCaseImpl.class.getSimpleName();

    private final KonashiOtaUpdaterStore store;
    private final DevicesRepository repo;

    @Inject
    ScanDevicesUseCaseImpl(KonashiOtaUpdaterStore store, DevicesRepository repo) {
        this.store = store;
        this.repo = repo;
    }

    @Override
    public Completable execute() {
        return repo.scan()
                .doOnNext(store::addDevice)
                .toCompletable();
    }
}
