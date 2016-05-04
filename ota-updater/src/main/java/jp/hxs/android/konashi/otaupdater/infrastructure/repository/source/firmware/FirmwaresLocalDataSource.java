package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.firmware;

import java.util.List;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.infrastructure.api.FirmwaresApi;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class FirmwaresLocalDataSource implements FirmwaresDataSource {
    public static final String TAG = FirmwaresLocalDataSource.class.getSimpleName();

    private final FirmwaresApi api;

    FirmwaresLocalDataSource(FirmwaresApi api) {
        this.api = api;
    }

    @Override
    public Single<List<Firmware>> getAll() {
        return api.getAll();
    }
}
