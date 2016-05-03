package jp.hxs.android.konashi.otaupdater.domain.entity;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public class Konashi {
    public static final String TAG = Konashi.class.getSimpleName();

    public final String name;
    public final String revision;

    public Konashi(String name, String revision) {
        this.name = name;
        this.revision = revision;
    }
}
