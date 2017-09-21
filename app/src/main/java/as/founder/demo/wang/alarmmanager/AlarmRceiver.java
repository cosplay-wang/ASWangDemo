package as.founder.demo.wang.alarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by zhiwei.wang on 2016/7/11.
 */
public class AlarmRceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          //setAlarmTime(context, System.currentTimeMillis() + 15, "自定义action", 15);
            setAlarmRept(context);
        }


        Toast.makeText(context,intent.getAction()+"windowstgf5tf5t",Toast.LENGTH_SHORT).show();;
        Log.i("calender","z执行了定时任务");

    }
    private void setAlarmRept(Context context){
        Intent intent = new Intent(context, AlarmRceiver.class);
        // 设置intent的动作,识别当前设置的是哪一个闹铃,有利于管理闹铃的关闭


        intent.setAction("action");
        // 用广播管理闹铃
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //  PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        // 获取闹铃管理
        AlarmManager am = (AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
        // 设置闹钟


//        Log.i("calendar", year + "年" + month + "月" + day + "天" + time + "时" + min + "分" + xx + "秒");
//
//        c.set(year, month, day, time, min, xx);
        //am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (android.os.Build.VERSION.SDK_INT < 19) {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
            //  am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),10*1000, pi);
        } else {
            //  am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
            am.setWindow(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+5000,0, pi);
        }
    }
}
