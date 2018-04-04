package as.founder.demo.wang.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessengerActivity extends AppCompatActivity {
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.stop)
    TextView stop;
    private Messenger mService;
    Messenger messenger = new Messenger(new MessengerHandler());
    @BindView(R.id.show_result)
    TextView showResult;
    boolean isSend = true;
    @OnClick({R.id.stop,R.id.send})
    public void clickSend(View view) {
        switch(view.getId()){
            case R.id.send:

                Message msgFromClient = Message.obtain(null, 1, 11, 22);
                msgFromClient.replyTo = messenger;
                //往服务端发送消息
                try {
                    if(isSend){
                        mService.send(msgFromClient);
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stop:
                isSend = false;
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        bindServiceInvoked();
    }


    private void bindServiceInvoked() {
        Intent intent = new Intent();
        intent.setAction("com.cosplay.aidl.messenger");
        Intent eintent = new Intent(getExplicitIntent(this,intent));
        bindService(eintent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message messageFormServer) {

            switch (messageFormServer.what) {
                case 1:
                    showResult.setText(showResult.getText() + "=>" + messageFormServer.arg2);
                    break;
            }

            super.handleMessage(messageFormServer);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
