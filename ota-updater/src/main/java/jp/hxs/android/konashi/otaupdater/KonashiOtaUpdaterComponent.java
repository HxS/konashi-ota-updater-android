package jp.hxs.android.konashi.otaupdater;

import javax.inject.Singleton;

import dagger.Component;
import jp.hxs.android.konashi.otaupdater.data.store.StoreModule;
import jp.hxs.android.konashi.otaupdater.data.usecase.UseCaseModule;
import jp.hxs.android.konashi.otaupdater.infrastructure.api.ApiModule;
import jp.hxs.android.konashi.otaupdater.infrastructure.repository.RepositoryModule;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
@Component(
        modules = {
                ApiModule.class,
                StoreModule.class,
                RepositoryModule.class,
                UseCaseModule.class,

                KonashiOtaUpdaterModule.class
        }
)
public interface KonashiOtaUpdaterComponent {
}
