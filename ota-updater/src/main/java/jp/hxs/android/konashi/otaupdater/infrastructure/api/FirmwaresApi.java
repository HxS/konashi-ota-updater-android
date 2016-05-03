package jp.hxs.android.konashi.otaupdater.infrastructure.api;

import java.util.List;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import retrofit2.http.GET;
import rx.Single;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public interface FirmwaresApi {
    @GET("/api/firmwares/list")
    Single<List<Firmware>> getAll();
}
