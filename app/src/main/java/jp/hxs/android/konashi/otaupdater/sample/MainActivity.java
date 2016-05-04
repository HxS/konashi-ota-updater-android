package jp.hxs.android.konashi.otaupdater.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jp.hxs.android.konashi.otaupdater.presentation.fragment.otaupdater.KonashiOtaUpdaterFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, KonashiOtaUpdaterFragment.newInstance())
                .commit();
    }
}
