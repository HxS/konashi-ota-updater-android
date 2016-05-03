package jp.hxs.android.konashi.otaupdater.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import javax.inject.Inject;

import jp.hxs.android.konashi.otaupdater.R;
import jp.hxs.android.konashi.otaupdater.domain.entity.Firmware;
import jp.hxs.android.konashi.otaupdater.domain.store.KonashiOtaUpdaterStore;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public class FirmwaresAdapter extends ArrayAdapter<Firmware> {
    public static final String TAG = FirmwaresAdapter.class.getSimpleName();

    private static final int LAYOUT_RES_ID = R.layout.list_item_firmware;

    @Inject
    public FirmwaresAdapter(Context context, KonashiOtaUpdaterStore store) {
        super(context, LAYOUT_RES_ID);
        store.observeFirmwares()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::addAll)
                .subscribe(_l -> notifyDataSetChanged());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_firmware, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.konashi_updater_text_title);
            holder.author = (TextView) convertView.findViewById(R.id.konashi_updater_text_author);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Firmware firmware = getItem(position);

        holder.title.setText(firmware.title);
        holder.author.setText(firmware.author);

        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView author;
    }
}
