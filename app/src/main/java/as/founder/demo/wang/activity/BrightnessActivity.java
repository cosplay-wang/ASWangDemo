package as.founder.demo.wang.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BrightnessActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.sys_add)
    TextView sysAdd;
    @BindView(R.id.sys_lose)
    TextView sysLose;
    @BindView(R.id.app_add)
    TextView appAdd;
    @BindView(R.id.app_lose)
    TextView appLose;
    @BindView(R.id.window_add)
    TextView windowAdd;
    @BindView(R.id.window_lose)
    TextView swindowLose;//

    @BindView(R.id.seekbar)
    SeekBar seekBar;
    @OnClick({R.id.sys_add,R.id.sys_lose,R.id.window_add,R.id.window_lose})
    public void setOnClick(View view){
        DealOnClick(view);

    }

    // 设置默认为横屏SCREEN_ORIENTATION_LANDSCAPE,竖屏SCREEN_ORIENTATION_PORTRAIT
    private int L_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    private int P_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    private int N_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        unbinder = ButterKnife.bind(this);
        setRequestedOrientation(L_ORIENTATION);
        try {
            int curBrighr = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            setSpLight(curBrighr);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("dddd",progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void DealOnClick(View view){
        if(view.getId() == R.id.sys_add){

            try {
                setScrennManualMode();
                //得到当前系统的亮度值
                //返回的亮度值是处于0-255之间的整型数值。
                int curBrighr = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                // 设置系统亮度
                if(curBrighr>=255){
                    curBrighr = 255;
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,curBrighr);
                }else if(curBrighr<0){
                    curBrighr = 0;
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,curBrighr);
                }else{
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS,curBrighr+50);
            }
                setRequestedOrientation(P_ORIENTATION);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
        }else if(view.getId() == R.id.sys_lose) {
            try {
                setScrennManualMode();
                //得到当前系统的亮度值
                //返回的亮度值是处于0-255之间的整型数值。
                int curBrighr = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                // 设置系统亮度
                if(curBrighr>255){
                    curBrighr = 255;
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,curBrighr);
                }else if(curBrighr<=0){
                    curBrighr = 0;
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,curBrighr);
                }else{
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,curBrighr-50);
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

        }else if(view.getId() == R.id.window_add)
        {

            //返回的亮度值是处于0-255之间的整型数值。
           int curBrighr = getSpLight();
            // 设置系统亮度
                if(curBrighr>=255){
                    curBrighr = 255;
                    changeAppBrightness(curBrighr);
                    setSpLight(curBrighr);
                }else if(curBrighr<0){
                    curBrighr = 0;
                    setSpLight(curBrighr);
                    changeAppBrightness(curBrighr);
                }else{
                  curBrighr =   curBrighr+50;
                changeAppBrightness(curBrighr);

            }
            setSpLight(curBrighr);



        }
        else if(view.getId() == R.id.window_lose)
        {

                //返回的亮度值是处于0-255之间的整型数值。
                int curBrighr = getSpLight();
                // 设置系统亮度
                if(curBrighr>255){
                    curBrighr = 255;
                    changeAppBrightness(curBrighr);
                    setSpLight(curBrighr);
                }else if(curBrighr<=0){
                    curBrighr = 0;
                    changeAppBrightness(curBrighr);
                    setSpLight(curBrighr);
                }else{
                    if(curBrighr<=50){
                        setSpLight(0);
                        changeAppBrightness(0);
                    }else{
                        curBrighr = curBrighr - 50;
                        setSpLight(curBrighr);
                        changeAppBrightness(curBrighr);
                    }

                }


        }
    }

    /**
     * 设置屏幕亮度调节模式为手动模式。
     * Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC：值为1，自动调节亮度。
     * Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL：值为0，手动模式。
     */
    public void setScrennManualMode() {
        ContentResolver contentResolver = getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 根据亮度值修改当前window亮度
     public void changeAppBrightness(int brightness) {
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
           if (brightness == -1) {
               lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
               } else {
               lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
               }
             window.setAttributes(lp);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setSpLight(int cur){
        SharedPreferences sharedPreferences = getSharedPreferences("light",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("light",cur);
        editor.commit();
    }
    private int getSpLight(){
        SharedPreferences sharedPreferences = getSharedPreferences("light",Activity.MODE_PRIVATE);
        int light = sharedPreferences.getInt("light",-1);
        return  light;
    }
}
