package jp.hxs.android.konashi.otaupdater.domain.entity;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public class Device {
    public static final String TAG = Device.class.getSimpleName();

    public final String name;
    public final String revision;

    public Device(String name, String revision) {
        this.name = name;
        this.revision = revision;
    }
}
