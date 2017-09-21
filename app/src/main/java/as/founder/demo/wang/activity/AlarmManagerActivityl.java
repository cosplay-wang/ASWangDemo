package as.founder.demo.wang.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import as.founder.demo.wang.R;
import as.founder.demo.wang.alarmmanager.AlarmRceiver;

public class AlarmManagerActivityl extends AppCompatActivity implements View.OnClickListener {
    Button start, close;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager_activityl);
        start = (Button) findViewById(R.id.start);
        close = (Button) findViewById(R.id.close);
        start.setOnClickListener(this);
        close.setOnClickListener(this);
        URL url = null;
        try {
            url = new URL("ddddddddd");
            try {
                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);    //获取年
        int month = c.get(Calendar.MONTH);   //获取月份，0表示1月份
        int day = c.get(Calendar.DAY_OF_MONTH);    //获取当前天数

        int time = c.get(Calendar.HOUR_OF_DAY);       //获取当前小时
        int min = c.get(Calendar.MINUTE);          //获取当前分钟
        int xx = c.get(Calendar.SECOND);          //获取当前秒

        switch (v.getId()) {
            case R.id.start: {
                Intent intent = new Intent(this, AlarmRceiver.class);
                // 设置intent的动作,识别当前设置的是哪一个闹铃,有利于管理闹铃的关闭


                intent.setAction("action");
                // 用广播管理闹铃
                PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
                //  PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
                // 获取闹铃管理
                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                // 设置闹钟


                Log.i("calendar", year + "年" + month + "月" + day + "天" + time + "时" + min + "分" + xx + "秒");

                c.set(year, month, day, time, min, xx);
                //am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                if (android.os.Build.VERSION.SDK_INT < 19) {
                    am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                  //  am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),10*1000, pi);
                } else {
                    am.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pi);
                   // am.setWindow(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),0, pi);
                }
//                am.setWindow(AlarmManager.RTC_WAKEUP,
//                        calendar.getTimeInMillis(), 10 * 1000, pi);
                break;
            }


            case R.id.close: {


                Intent intent = new Intent(this, AlarmRceiver.class);
                // 设置intent的动作,识别当前设置的是哪一个闹铃,有利于管理闹铃的关闭
                intent.setAction("action");
                // 用广播管理闹铃
                PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
                //  PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
                // 获取闹铃管理
                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);


                Log.i("结束" + "calendar", year + "年" + month + "月" + day + "天" + time + "时" + min + "分" + xx + "秒");


                // am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                am.cancel(pi);
                break;
            }

        }

    }
}
