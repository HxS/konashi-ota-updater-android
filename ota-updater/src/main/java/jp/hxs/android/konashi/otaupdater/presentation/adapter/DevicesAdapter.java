package jp.hxs.android.konashi.otaupdater.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.R;
import jp.hxs.android.konashi.otaupdater.domain.entity.Device;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public class DevicesAdapter extends ArrayAdapter<Device> {
    public static final String TAG = DevicesAdapter.class.getSimpleName();

    private static final int LAYOUT_RES_ID = R.layout.list_item_device;

    @Inject
    public DevicesAdapter(Context context, KonashiOtaUpdaterStore store) {
        super(context, LAYOUT_RES_ID);
        store.observeDevices()
                .distinct(device -> device.address)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::add)
                .subscribe(_l -> notifyDataSetChanged());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_device, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.konashi_updater_text_name);
            holder.address = (TextView) convertView.findViewById(R.id.konashi_updater_text_address);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Device device = getItem(position);

        holder.name.setText(device.name);
        holder.address.setText(device.address);

        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView address;
    }
}
