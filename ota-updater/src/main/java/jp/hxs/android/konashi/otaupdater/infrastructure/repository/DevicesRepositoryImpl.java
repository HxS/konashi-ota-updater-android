package jp.hxs.android.konashi.otaupdater.infrastructure.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.repository.DevicesRepository;
import jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.device.DevicesDataSourceFactory;
import rx.Observable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class DevicesRepositoryImpl implements DevicesRepository {
    public static final String TAG = DevicesRepositoryImpl.class.getSimpleName();

    private final DevicesDataSourceFactory dataSourceFactory;

    @Inject
    public DevicesRepositoryImpl(DevicesDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public Observable<Device> scan() {
        return dataSourceFactory.createRealDataSource().scan();
    }
}
