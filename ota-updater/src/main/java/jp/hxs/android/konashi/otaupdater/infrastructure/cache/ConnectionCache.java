package jp.hxs.android.konashi.otaupdater.infrastructure.cache;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.izumin.android.bletia.rx.RxBletia;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class ConnectionCache {
    public static final String TAG = ConnectionCache.class.getSimpleName();

    private final Context context;
    private final Map<String, RxBletia> map = new HashMap<>();

    @Inject
    public ConnectionCache(Context context) {
        this.context = context;
    }

    public Single<RxBletia> put(BluetoothDevice device) {
        final RxBletia bletia = new RxBletia(context);
        return bletia.connect(device)
                .flatMap(_v -> bletia.discoverServices())
                .map(_v -> bletia)
                .doOnNext(b -> map.put(device.getAddress(), b))
                .toSingle();
    }

    public RxBletia get(String address) {
        return map.get(address);
    }

    public boolean contains(BluetoothDevice device) {
        return contains(device.getAddress());
    }

    public boolean contains(String address) {
        return map.containsKey(address);
    }

    public Completable remove(String address) {
        return map.remove(address)
                .disconnect()
                .toCompletable();
    }

    public Completable clear() {
        return Observable.from(new ArrayList<>(map.keySet()))
                .map(map::remove)
                .flatMap(RxBletia::disconnect)
                .toCompletable();
    }
}
