package jp.hxs.android.konashi.otaupdater.domain.entity;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import java.util.UUID;

import info.izumin.android.bletia.rx.RxBletia;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public enum GattCharacteristic {
    SOFTWARE_REVISION   (GattService.DEVICE_INFO,   "00002A28-0000-1000-8000-00805f9b34fb"),
    OTA_CONTROL_POINT   (GattService.OTA,           "0f7a29bb-a965-4279-8546-b56e981c008b"),
    OTA_DATA            (GattService.OTA,           "8e922cce-eec6-47b0-b46d-09563a8da638")
    ;

    private final GattService gattService;
    private final UUID uuid;

    GattCharacteristic(GattService gattService, String uuidString) {
        this.gattService = gattService;
        this.uuid = UUID.fromString(uuidString);
    }

    public GattService getGattService() {
        return gattService;
    }

    public UUID getUuid() {
        return uuid;
    }

    public BluetoothGattService getGattService(RxBletia bletia) {
        return bletia.getService(gattService.getUuid());
    }

    public BluetoothGattCharacteristic get(RxBletia bletia) {
        return getGattService(bletia).getCharacteristic(uuid);
    }
}
