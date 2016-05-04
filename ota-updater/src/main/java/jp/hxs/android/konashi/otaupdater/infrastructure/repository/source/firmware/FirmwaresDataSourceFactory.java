package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.firmware;

import javax.inject.Inject;
import javax.inject.Singleton;

import jp.hxs.android.konashi.otaupdater.infrastructure.api.FirmwaresApi;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class FirmwaresDataSourceFactory {
    public static final String TAG = FirmwaresDataSourceFactory.class.getSimpleName();

    private final FirmwaresApi api;

    @Inject
    public FirmwaresDataSourceFactory(FirmwaresApi api) {
        this.api = api;
    }

    public FirmwaresDataSource createLocalDataSource() {
        return new FirmwaresLocalDataSource(api);
    }
}
