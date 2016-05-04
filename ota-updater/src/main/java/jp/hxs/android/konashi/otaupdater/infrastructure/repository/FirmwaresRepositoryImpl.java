package jp.hxs.android.konashi.otaupdater.infrastructure.repository;

import java.util.List;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.domain.repository.FirmwaresRepository;
import jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.firmware.FirmwaresDataSourceFactory;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class FirmwaresRepositoryImpl implements FirmwaresRepository {
    public static final String TAG = FirmwaresRepositoryImpl.class.getSimpleName();

    private final FirmwaresDataSourceFactory dataSourceFactory;

    @Inject
    FirmwaresRepositoryImpl(FirmwaresDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public Single<List<Firmware>> getAll() {
        return dataSourceFactory.createLocalDataSource().getAll();
    }

    @Override
    public Single<byte[]> getBinary(Firmware firmware) {
        return dataSourceFactory.createLocalDataSource().getBinary(firmware);
    }
}
