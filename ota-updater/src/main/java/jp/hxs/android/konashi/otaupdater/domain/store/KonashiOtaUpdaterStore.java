package jp.hxs.android.konashi.otaupdater.domain.store;

import java.util.List;

import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import rx.Observable;

/**
 * Created by izumin on 5/3/2016 AD.
 */
public interface KonashiOtaUpdaterStore {
    void update(List<Firmware> nextValue);
    Observable<List<Firmware>> observeFirmwares();

    void update(Firmware firmware);
    Firmware getFirmware();
    Observable<Firmware> observeFirmware();

    void addDevice(Device newDevice);
    Observable<Device> observeDevices();
}
