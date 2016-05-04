package jp.hxs.android.konashi.otaupdater.domain.usecase;

import rx.Observable;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public interface UploadFirmwareToKonashiUseCase extends UseCase {
    Observable<Integer> execute();
}
