package jp.hxs.android.konashi.otaupdater.infrastructure.repository.source.firmware;

import java.io.IOException;
import java.util.List;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.infrastructure.api.FirmwaresApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
class FirmwaresLocalDataSource implements FirmwaresDataSource {
    public static final String TAG = FirmwaresLocalDataSource.class.getSimpleName();

    private final FirmwaresApi api;
    private final OkHttpClient okHttpClient;

    FirmwaresLocalDataSource(FirmwaresApi api, OkHttpClient okHttpClient) {
        this.api = api;
        this.okHttpClient = okHttpClient;
    }

    @Override
    public Single<List<Firmware>> getAll() {
        return api.getAll();
    }

    @Override
    public Single<byte[]> getBinary(Firmware firmware) {
        return Single.create(subscriber -> {
            Request request = new Request.Builder()
                    .url(firmware.url)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    subscriber.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    subscriber.onSuccess(response.body().bytes());
                }
            });
        });
    }
}
