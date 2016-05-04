package jp.hxs.android.konashi.otaupdater.data.usecase;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.repository.DevicesRepository;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.domain.usecase.ConnectDeviceUseCase;
import rx.Completable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class ConnectDeviceUseCaseImpl implements ConnectDeviceUseCase {
    public static final String TAG = ConnectDeviceUseCaseImpl.class.getSimpleName();

    private final KonashiOtaUpdaterStore store;
    private final DevicesRepository repo;

    @Inject
    ConnectDeviceUseCaseImpl(KonashiOtaUpdaterStore store, DevicesRepository repo) {
        this.store = store;
        this.repo = repo;
    }

    @Override
    public Completable execute(Device device) {
        return repo.connect(device).doOnSuccess(store::update)
                .toObservable().toCompletable();
    }
}
