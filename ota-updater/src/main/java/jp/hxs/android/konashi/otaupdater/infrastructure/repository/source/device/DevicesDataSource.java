package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device;

import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public interface DevicesDataSource {
    Observable<Device> scan();
    Single<ConnectedDevice> connect(Device device);
    Completable disconnect(Device device);

    Observable<Integer> update(Device device, byte[] binary);
}
