package jp.hxs.android.konashi.otaupdater.domain.entity;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public class ConnectedDevice extends Device {
    public static final String TAG = ConnectedDevice.class.getSimpleName();

    public final String revision;

    public ConnectedDevice(String name, String address, String revision) {
        super(name, address);
        this.revision = revision;
    }
}
