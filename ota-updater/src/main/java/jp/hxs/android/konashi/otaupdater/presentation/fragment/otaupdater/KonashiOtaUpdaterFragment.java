package jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.BuildConfig;
import jp.hxs.android.konashi.otaupdater.DaggerKonashiOtaUpdaterComponent;
import jp.hxs.android.konashi.otaupdater.KonashiOtaUpdaterComponent;
import jp.hxs.android.konashi.otaupdater.KonashiOtaUpdaterModule;
import jp.hxs.android.konashi.otaupdater.R;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.infrastructure.api.ApiModule;


/**
 * A simple {@link Fragment} subclass.
 */
public class KonashiOtaUpdaterFragment extends Fragment implements KonashiOtaUpdaterView {
    public static final String TAG = KonashiOtaUpdaterFragment.class.getSimpleName();

    public static KonashiOtaUpdaterFragment newInstance() {
        return new KonashiOtaUpdaterFragment();
    }

    private KonashiOtaUpdaterComponent component;
    private UpdaterFragmentComponent subcomponent;

    private TextView textTitle, textAuthor, textDescription;
    private TextView textName, textRevision;

    @Inject KonashiOtaUpdaterHandlers handlers;
    @Inject KonashiOtaUpdaterPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_konashi_ota_updater, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupComponent();
        textTitle       = (TextView) view.findViewById(R.id.konashi_edit_title);
        textAuthor      = (TextView) view.findViewById(R.id.konashi_edit_author);
        textDescription = (TextView) view.findViewById(R.id.konashi_edit_description);
        textName        = (TextView) view.findViewById(R.id.konashi_edit_name);
        textRevision    = (TextView) view.findViewById(R.id.konashi_edit_revision);

        view.findViewById(R.id.konashi_updater_btn_select_firmware)
                .setOnClickListener(v -> handlers.onSelectFirmwareClicked());
        view.findViewById(R.id.konashi_updater_btn_connect)
                .setOnClickListener(v -> handlers.onConnectClicked());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        handlers.onResume();
    }

    @Override
    public void onPause() {
        handlers.onPause();
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.konashi_updater, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.konashi_menu_update) {
            handlers.onUpdateClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ================================================================
    // KonashiOtaUpdaterView
    // ================================================================

    @Override
    public void setFirmware(Firmware firmware) {
        textTitle.setText(firmware.title);
        textAuthor.setText(firmware.author);
        textDescription.setText(firmware.description);
    }

    @Override
    public void setKonashi(Device device) {
        textName.setText(device.name);
        textRevision.setText(device.revision);
    }

    // ================================================================
    // private methods
    // ================================================================

    private void setupComponent() {
        component = DaggerKonashiOtaUpdaterComponent.builder()
                .konashiOtaUpdaterModule(new KonashiOtaUpdaterModule(getActivity().getApplication()))
                .apiModule(new ApiModule(BuildConfig.WEB_API_ENDPOINT))
                .build();
        subcomponent = component.plus(new UpdaterFragmentModule(this));
        subcomponent.inject(this);
    }
}
