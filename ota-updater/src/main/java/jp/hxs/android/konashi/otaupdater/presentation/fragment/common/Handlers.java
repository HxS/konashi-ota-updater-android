package jp.hxs.android.konashi.otaupdater.presentation.fragment.common;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public abstract class Handlers {
    public static final String TAG = Handlers.class.getSimpleName();

    private CompositeSubscription subscriptions;

    public void onResume() {
        subscriptions = new CompositeSubscription();
    }

    public void onPause() {
        subscriptions.unsubscribe();
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
