package jp.hxs.android.konashi.otaupdater.presentation.dialog.selectfirmware;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.KonashiOtaUpdaterComponent;
import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.presentation.adapter.FirmwaresAdapter;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public class SelectFirmwareDialog extends DialogFragment {
    public static final String TAG = SelectFirmwareDialog.class.getSimpleName();

    public static SelectFirmwareDialog newInstance() {
        return new SelectFirmwareDialog();
    }

    @Inject FirmwaresAdapter adapter;

    private Listener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getTargetFragment() instanceof Listener) {
            listener = (Listener) getTargetFragment();
        } else {
            throw new RuntimeException();
        }
        setupComponent();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setAdapter(adapter, (dialog, which) -> listener.onFirmwareSelected(adapter.getItem(which)))
                .create();
    }

    private void setupComponent() {
        listener.getKonashiOtaUpdaterComponent()
                .plus(new SelectFirmwareModule())
                .inject(this);
    }

    public interface Listener {
        KonashiOtaUpdaterComponent getKonashiOtaUpdaterComponent();
        void onFirmwareSelected(Firmware firmware);
    }
}
