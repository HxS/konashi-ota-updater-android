package jp.hxs.android.konashi.otaupdater.domain.repository;

import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import rx.Observable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public interface DevicesRepository {
    Observable<Device> scan();
}
