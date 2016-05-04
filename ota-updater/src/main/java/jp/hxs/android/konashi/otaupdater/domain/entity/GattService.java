package jp.hxs.android.konashi.otaupdater.domain.entity;

import java.util.UUID;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public enum GattService {
    KONASHI     ("229bff00-03fb-40da-98a7-b0def65c2d4b"),
    DEVICE_INFO ("0000180a-0000-1000-8000-00805f9b34fb"),
    OTA         ("3908d54f-0c55-4ea1-8fd1-8394a172257d")
    ;

    private final UUID uuid;

    GattService(String uuidString) {
        uuid = UUID.fromString(uuidString);
    }

    public UUID getUuid() {
        return uuid;
    }
}
