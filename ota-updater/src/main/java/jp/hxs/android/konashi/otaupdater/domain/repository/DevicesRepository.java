package jp.hxs.android.konashi.otaupdater.domain.repository;

import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public interface DevicesRepository {
    Observable<Device> scan();
    Single<ConnectedDevice> connect(Device device);
    Completable disconnect(Device device);
}
