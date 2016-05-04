package jp.hxs.android.konashi.otaupdater.domain.repository;

import java.util.List;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public interface FirmwaresRepository {
    Single<List<Firmware>> getAll();
    Single<byte[]> getBinary(Firmware firmware);
}
