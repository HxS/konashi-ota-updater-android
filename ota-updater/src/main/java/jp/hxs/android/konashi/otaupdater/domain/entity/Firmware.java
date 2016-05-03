package jp.hxs.android.konashi.otaupdater.domain.entity;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public class Firmware {
    public static final String TAG = Firmware.class.getSimpleName();

    public final String title;
    public final String description;
    public final String author;
    public final String date;
    public final String url;
    public final String md5;
    public final String revision;

    public Firmware(String title,
                    String description,
                    String author,
                    String date,
                    String url,
                    String md5,
                    String revision) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.date = date;
        this.url = url;
        this.md5 = md5;
        this.revision = revision;
    }
}
