package jp.hxs.android.konashi.otaupdater.infrastructure.cache;

import android.bluetooth.BluetoothDevice;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.izumin.android.rxblescanner.ScanResultJB;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;

/**
 * Created by izumin on 5/4/2016 AD.
 */
@Singleton
public class BluetoothDeviceCache extends HashMap<String, BluetoothDevice> {
    public static final String TAG = BluetoothDeviceCache.class.getSimpleName();

    @Inject
    public BluetoothDeviceCache() {
    }

    public BluetoothDevice get(Device device) {
        return get(device.address);
    }

    public BluetoothDevice get(String address) {
        return super.get(address);
    }

    public BluetoothDevice put(ScanResultJB scanResultJB) {
        return put(scanResultJB.getDevice());
    }

    public BluetoothDevice put(BluetoothDevice device) {
        return put(device.getAddress(), device);
    }

    public boolean containsKey(ScanResultJB scanResultJB) {
        return containsKey(scanResultJB.getDevice());
    }

    public boolean containsKey(BluetoothDevice device) {
        return containsKey(device.getAddress());
    }

    public BluetoothDevice remove(ScanResultJB scanResultJB) {
        return remove(scanResultJB.getDevice());
    }

    public BluetoothDevice remove(BluetoothDevice device) {
        return remove(device.getAddress());
    }
}
