package jp.hxs.android.konashi.otaupdater.domain.usecase;

import rx.Completable;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public interface GetFirmwaresUseCase extends UseCase {
    Completable execute();
}
