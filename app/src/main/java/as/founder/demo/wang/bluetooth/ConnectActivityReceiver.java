package as.founder.demo.wang.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import as.founder.demo.wang.activity.BluetoochActivity;

/**
 * Created by zhiwei.wang on 2016/8/4.
 */
public class ConnectActivityReceiver extends BroadcastReceiver {

    String strPsw = "0";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(
                "android.bluetooth.device.action.PAIRING_REQUEST"))
        {
            BluetoothDevice btDevice = intent
                    .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            // byte[] pinBytes = BluetoothDevice.convertPinToBytes("1234");
            // device.setPin(pinBytes);
            Log.i("tag11111", "ddd");
            try
            {
                BuletoochUtils.setPin(btDevice.getClass(), btDevice, strPsw); // 手机和蓝牙采集器配对
                BuletoochUtils.createBond(btDevice.getClass(), btDevice);
                BuletoochUtils.cancelPairingUserInput(btDevice.getClass(), btDevice);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }


}
