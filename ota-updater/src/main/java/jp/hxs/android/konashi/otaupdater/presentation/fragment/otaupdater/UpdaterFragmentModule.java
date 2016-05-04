package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import dagger.Module;
import dagger.Provides;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Module
public class UpdaterFragmentModule {
    public static final String TAG = UpdaterFragmentModule.class.getSimpleName();

    private final KonashiOtaUpdaterView view;

    public UpdaterFragmentModule(KonashiOtaUpdaterView view) {
        this.view = view;
    }

    @Provides
    @UpdaterFragmentScope
    KonashiOtaUpdaterView konashiOtaUpdaterView() {
        return view;
    }
}
