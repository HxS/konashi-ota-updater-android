package jp.hxs.android.konashi.otaupdater.data.store;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Module
public class StoreModule {
    public static final String TAG = StoreModule.class.getSimpleName();

    @Provides
    @Singleton
    KonashiOtaUpdaterStore konashiOtaUpdaterStore(KonashiOtaUpdaterStoreImpl store) {
        return store;
    }
}
