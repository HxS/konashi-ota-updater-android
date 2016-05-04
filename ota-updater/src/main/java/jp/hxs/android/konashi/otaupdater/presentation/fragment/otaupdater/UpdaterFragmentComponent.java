package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import dagger.Subcomponent;

/**
 * Created by izumin on 5/3/2016 AD.
 */
@UpdaterFragmentScope
@Subcomponent(
        modules = UpdaterFragmentModule.class
)
public interface UpdaterFragmentComponent {
    void inject(KonashiOtaUpdaterFragment fragment);
}
