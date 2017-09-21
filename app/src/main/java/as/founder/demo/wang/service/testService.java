package as.founder.demo.wang.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class testService extends Service {
    public testService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       /// flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
       // this.startForeground(0,new Notification());
    }
//    class hh extends BroadcastReceiver{
//        Context context;
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            boolean dd = isOrderedBroadcast();
//
//            context.registerReceiver(hh.this,new IntentFilter());
//            context.sendBroadcast(new Intent(context,hh.class));
//            IntentFilter filter = new IntentFilter();
//            filter.setPriority(1000);
//            abortBroadcast();
//            setResultData("dddddddddd");
//            String jj  = getResultData();
//            context.sendOrderedBroadcast(new Intent(context,hh.class),"String receiver Permission");
//        }
//    }
}
