package jp.hxs.android.konashi.otaupdater.data.store;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by izumin on 5/3/2016 AD.
 */
class KonashiOtaUpdaterStoreImpl implements KonashiOtaUpdaterStore {
    public static final String TAG = KonashiOtaUpdaterStoreImpl.class.getSimpleName();

    private final Subject<Firmware, Firmware> firmwareSubject;

    private final Subject<List<Firmware>, List<Firmware>> firmwaresSubject;
    private Firmware firmware;

    @Inject
    public KonashiOtaUpdaterStoreImpl() {
        firmwaresSubject = new SerializedSubject<>(BehaviorSubject.create(new ArrayList<>()));
        firmwareSubject = new SerializedSubject<>(BehaviorSubject.create());
    }

    @Override
    public void update(List<Firmware> nextValue) {
        firmwaresSubject.onNext(nextValue);
    }

    @Override
    public Observable<List<Firmware>> observeFirmwares() {
        return firmwaresSubject;
    }

    @Override
    public void update(Firmware firmware) {
        this.firmware = firmware;
        this.firmwareSubject.onNext(firmware);
    }

    @Override
    public Firmware getFirmware() {
        return firmware;
    }

    @Override
    public Observable<Firmware> observeFirmware() {
        return firmwareSubject;
    }
}
