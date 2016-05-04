package jp.hxs.android.konashi.otaupdater.presentation.dialog.selectfirmware;

import dagger.Subcomponent;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@SelectFirmwareScope
@Subcomponent(
        modules = SelectFirmwareModule.class
)
public interface SelectFirmwareComponent {
    void inject(SelectFirmwareDialog dialog);
}
