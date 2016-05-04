package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import jp.hxs.android.konashi.otaupdater.presentation.fragment.common.Presenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@UpdaterFragmentScope
class KonashiOtaUpdaterPresenter extends Presenter {
    public static final String TAG = KonashiOtaUpdaterPresenter.class.getSimpleName();

    private final KonashiOtaUpdaterView view;
    private final KonashiOtaUpdaterStore store;

    @Inject
    public KonashiOtaUpdaterPresenter(KonashiOtaUpdaterView view, KonashiOtaUpdaterStore store) {
        this.view = view;
        this.store = store;
    }

    @Override
    public void onResume() {
        super.onResume();
        addSubscription(store.observeFirmware()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setFirmware));
        addSubscription(store.observeConnectedDevice()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setConnectedDevice));
    }
}
