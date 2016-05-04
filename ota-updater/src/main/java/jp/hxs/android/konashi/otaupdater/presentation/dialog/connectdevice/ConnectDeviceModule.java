package jp.hxs.android.konashi.otaupdater.presentation.dialog.connectdevice;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.presentation.adapter.DevicesAdapter;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Module
public class ConnectDeviceModule {
    public static final String TAG = ConnectDeviceModule.class.getSimpleName();

    @Provides
    DevicesAdapter devicesAdapter(Context context, KonashiOtaUpdaterStore store) {
        return new DevicesAdapter(context, store);
    }
}
