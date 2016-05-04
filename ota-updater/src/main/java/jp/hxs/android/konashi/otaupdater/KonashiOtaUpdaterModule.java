package jp.hxs.android.konashi.otaupdater;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.rxblescanner.RxBleScanner;

/**
 * Created by izumin on 5/3/2016 AD.
 */
@Module
public class KonashiOtaUpdaterModule {
    public static final String TAG = KonashiOtaUpdaterModule.class.getSimpleName();

    private final Application app;

    public KonashiOtaUpdaterModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context context() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    BluetoothManager bluetoothManager(Context context) {
        return (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
    }

    @Provides
    BluetoothAdapter bluetoothAdapter(BluetoothManager manager) {
        return manager.getAdapter();
    }

    @Provides
    RxBleScanner rxBleScanner(BluetoothAdapter adapter) {
        return new RxBleScanner(adapter);
    }
}
