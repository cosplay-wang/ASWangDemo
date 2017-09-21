package as.founder.demo.wang.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import as.founder.demo.wang.R;

public class ScreenDerectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_screen_derection);
        final int orientation = getResources().getConfiguration().orientation;
        final int rotation = getWindowManager().getDefaultDisplay().getOrientation();
        Log.i("ddddddddddddddd",orientation+"---------"+rotation);
    }
}
