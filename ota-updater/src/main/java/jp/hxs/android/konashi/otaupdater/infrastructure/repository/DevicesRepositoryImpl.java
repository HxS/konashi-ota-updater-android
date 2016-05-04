package jp.hxs.android.konashi.otaupdater.infrastructure.repository;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.repository.DevicesRepository;
import jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device.DevicesDataSourceFactory;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
class DevicesRepositoryImpl implements DevicesRepository {
    public static final String TAG = DevicesRepositoryImpl.class.getSimpleName();

    private final DevicesDataSourceFactory dataSourceFactory;

    @Inject
    DevicesRepositoryImpl(DevicesDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public Observable<Device> scan() {
        return dataSourceFactory.createRealDataSource().scan();
    }

    @Override
    public Single<ConnectedDevice> connect(Device device) {
        return dataSourceFactory.createRealDataSource().connect(device);
    }

    @Override
    public Completable disconnect(Device device) {
        return dataSourceFactory.createRealDataSource().disconnect(device);
    }

    @Override
    public Observable<Integer> update(Device device, byte[] binary) {
        return dataSourceFactory.createRealDataSource().update(device, binary)
                .scan(0, (i, j) -> i + j)
                .doOnNext(i -> Log.d(TAG, String.valueOf(i)));
    }
}
