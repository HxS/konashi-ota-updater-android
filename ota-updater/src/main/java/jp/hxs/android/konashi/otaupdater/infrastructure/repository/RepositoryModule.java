package jp.hxs.android.konashi.otaupdater.infrastructure.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.hxs.android.konashi.otaupdater.domain.repository.DevicesRepository;
import jp.hxs.android.konashi.otaupdater.domain.repository.FirmwaresRepository;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Module
public class RepositoryModule {
    public static final String TAG = RepositoryModule.class.getSimpleName();

    @Provides
    @Singleton
    FirmwaresRepository firmwaresRepository(FirmwaresRepositoryImpl repo) {
        return repo;
    }

    @Provides
    @Singleton
    DevicesRepository devicesRepository(DevicesRepositoryImpl repo) {
        return repo;
    }
}
