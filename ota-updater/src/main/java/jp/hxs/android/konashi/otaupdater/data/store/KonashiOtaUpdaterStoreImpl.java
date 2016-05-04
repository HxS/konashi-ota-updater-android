package jp.hxs.android.konashi.otaupdater.data.store;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.domain.entity.ConnectedDevice;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
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

    private final Subject<Device, Device> deviceSubject;

    private final Subject<ConnectedDevice, ConnectedDevice> connectedDeviceSubject;

    @Inject
    public KonashiOtaUpdaterStoreImpl() {
        firmwaresSubject = new SerializedSubject<>(BehaviorSubject.create(new ArrayList<>()));
        firmwareSubject = new SerializedSubject<>(BehaviorSubject.create());
        deviceSubject = new SerializedSubject<>(BehaviorSubject.create());
        connectedDeviceSubject = new SerializedSubject<>(BehaviorSubject.create());
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

    @Override
    public void addDevice(Device newDevice) {
        deviceSubject.onNext(newDevice);
    }

    @Override
    public Observable<Device> observeDevices() {
        return deviceSubject;
    }

    @Override
    public void update(ConnectedDevice device) {
        connectedDeviceSubject.onNext(device);
    }

    @Override
    public ConnectedDevice getConnectedDevice() {
        return connectedDeviceSubject.first().toBlocking().first();
    }

    @Override
    public Observable<ConnectedDevice> observeConnectedDevice() {
        return connectedDeviceSubject;
    }
}
