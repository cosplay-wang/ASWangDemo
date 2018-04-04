package as.founder.demo.wang.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.ListView;

public class MessengerService extends Service {
    private Messenger messenger = new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();
    }
    class  MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msgFromClient) {
            messenger = msgFromClient.replyTo;
            Message messageToClient = Message.obtain(msgFromClient);
            switch (msgFromClient.what){
                case 1:
                    messageToClient.what = 1;
                    messageToClient.arg2 = msgFromClient.arg1 + msgFromClient.arg2;
                    try {
                        Thread.sleep(2500);
                        messenger.send(messageToClient);
                      //  messageToClient.setData();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msgFromClient);
        }
    }
}
