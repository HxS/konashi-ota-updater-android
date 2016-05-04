package jp.hxs.android.konashi.otaupdater.presentation.dialog.selectfirmware;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.presentation.adapter.FirmwaresAdapter;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Module
public class SelectFirmwareModule {
    public static final String TAG = SelectFirmwareModule.class.getSimpleName();

    @Provides
    FirmwaresAdapter firmwaresAdapter(Context context, KonashiOtaUpdaterStore store) {
        return new FirmwaresAdapter(context, store);
    }
}
