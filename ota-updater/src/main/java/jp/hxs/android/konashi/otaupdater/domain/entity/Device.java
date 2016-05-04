package jp.hxs.android.konashi.otaupdater.domain.entity;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public class Device {
    public static final String TAG = Device.class.getSimpleName();

    public final String name;
    public final String address;

    public Device(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
