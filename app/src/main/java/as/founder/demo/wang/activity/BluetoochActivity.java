package as.founder.demo.wang.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import as.founder.demo.wang.R;
import as.founder.demo.wang.bluetooth.BuletoochUtils;

public class BluetoochActivity extends AppCompatActivity implements View.OnClickListener {
    TextView openTextv, closeTextv, systemTextv, searchTextv, searchEquipmentTextv, stopSearchEquipmentTextv, bluetoochReceiverTextv, bluetoochNameShow, connectedBluetoochNameshowTextv, connectedBlue_tooch_name, matchBluetoochNameTextv;
    String blueToochName = "设备名字是：";
    List<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
    // 创建一个接收ACTION_FOUND广播的BroadcastReceiver
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 发现设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 从Intent中获取设备对象
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                deviceList.add(device);
                Toast.makeText(getApplicationContext(), "收到foundAction", Toast.LENGTH_LONG).show();
                ;
                // 将设备名称和地址放入array adapter，以便在ListView中显示
                blueToochName = blueToochName + "\n" + device.getName() + "\n" + device.getAddress();
                Message message = handler.obtainMessage();
                message.obj = blueToochName;
                message.what = 0;
                handler.sendMessage(message);
                //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());9999999999999999999999
            }
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                bluetoochNameShow.setText(blueToochName);
            }
        }
    };

    /**
     * hffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooch);
        openTextv = (TextView) findViewById(R.id.open_blue);
        closeTextv = (TextView) findViewById(R.id.close_blue);
        systemTextv = (TextView) findViewById(R.id.system_blue);
        searchTextv = (TextView) findViewById(R.id.search_blue);
        searchEquipmentTextv = (TextView) findViewById(R.id.search_blue_equipment);
        stopSearchEquipmentTextv = (TextView) findViewById(R.id.stop_search_blue_equipment);
        bluetoochReceiverTextv = (TextView) findViewById(R.id.bluetooch_receiver);
        bluetoochNameShow = (TextView) findViewById(R.id.blue_tooch_name_show);
        connectedBluetoochNameshowTextv = (TextView) findViewById(R.id.connected_blue_tooch_name_show);
        connectedBlue_tooch_name = (TextView) findViewById(R.id.connected_blue_tooch_name);
        matchBluetoochNameTextv = (TextView) findViewById(R.id.match_blue_tooch_name);
        openTextv.setOnClickListener(this);
        closeTextv.setOnClickListener(this);
        systemTextv.setOnClickListener(this);
        searchTextv.setOnClickListener(this);
        searchEquipmentTextv.setOnClickListener(this);
        stopSearchEquipmentTextv.setOnClickListener(this);
        bluetoochReceiverTextv.setOnClickListener(this);
        connectedBluetoochNameshowTextv.setOnClickListener(this);
        connectedBlue_tooch_name.setOnClickListener(this);
        matchBluetoochNameTextv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_blue: {
                openBluetooch();
                break;
            }
            case R.id.close_blue: {
                closeBluetooch();
                break;
            }
            case R.id.system_blue: {
                systemBluetooch();
                break;
            }
            case R.id.search_blue: {
                searchBluetooch();
                break;
            }
            case R.id.search_blue_equipment: {
                searchBluetoochEquipment();
                Toast.makeText(getApplicationContext(), "开始搜索设备", Toast.LENGTH_LONG).show();
                ;
                break;
            }
            case R.id.stop_search_blue_equipment: {
                stopsearchBluetoochEquipment();
                bluetoochNameShow.setText("重置：：：：；");
                break;
            }
            case R.id.bluetooch_receiver: {

                // 注册BroadcastReceiver
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter); // 不要忘了之后解除绑定
                Toast.makeText(getApplicationContext(), "广播注册成功", Toast.LENGTH_LONG).show();
                ;
                break;
            }
            case R.id.connected_blue_tooch_name_show: {
                connectedEquipments();
                connectedBluetoochNameshowTextv.setText(blueToochName);
                break;
            }
            case R.id.connected_blue_tooch_name: {
                if (deviceList == null || deviceList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "没有已经配对过的设备！！！", Toast.LENGTH_LONG).show();
                } else {

                        Toast.makeText(getApplicationContext(), "连接配对过的设备！！！" + deviceList.get(0).getName(), Toast.LENGTH_LONG).show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connect(deviceList.get(0));
                        }
                    }) .start();;


                    break;
                }
            }
            case R.id.match_blue_tooch_name: {

                Method createBondMethod = null;
                try {
                    createBondMethod = BluetoothDevice.class.getMethod("createBond");
                    if (deviceList.size() == 0 || deviceList == null) {
                        return;
                    } else {
                        for (int i = 0; i < deviceList.size(); i++) {
                            if (deviceList.get(i).getName().contains("5210")||deviceList.get(i).getName().contains("小米")) {




//                                BluetoothDevice btDev =deviceList.get(i);
////                                String str = lstDevices.get(arg2);
////                                String[] values = str.split("\\|");
//                                try {
//                                    Boolean returnValue = false;
//                                    if (btDev.getBondState() == BluetoothDevice.BOND_NONE) {
//
//                                        BuletoochUtils.pair(btDev.getAddress(), "1234");
//                                    }else if(btDev.getBondState() == BluetoothDevice.BOND_BONDED){
//                                        connect(btDev);
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }














                               createBondMethod.invoke(deviceList.get(i));
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
        }

    }

    private void openBluetooch() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.enable();
    }

    private void closeBluetooch() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.disable();
    }

    private void systemBluetooch() {
        // 跳转到系统 Bluetooth 设置

        this.startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
//        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//        startActivity(intent);


    }

    private void searchBluetooch() {
        //Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //打开本机的蓝牙发现功能（默认打开120秒，可以将时间最多延长至300秒）
        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);//设置持续时间（最多300秒）
        startActivity(discoveryIntent);


    }

    private void searchBluetoochEquipment() {
        // startDiscovery()方法是一个异步方法，调用后会立即返回。该方法会进行对其他蓝牙设备的搜索，该过程会持续12秒。该方法调用后，搜索过程实际上是在一个System Service中进行的，
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter.isEnabled()) {
            adapter.startDiscovery();
        } else {
            Toast.makeText(getApplicationContext(), "蓝牙未开启，请先开启蓝牙~~~~~~····", Toast.LENGTH_LONG).show();
            ;

        }

    }

    private void stopsearchBluetoochEquipment() {
        // cancelDiscovery()方法来停止搜索（该方法可以在未执行discovery请求时调用）。
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.cancelDiscovery();
    }

    private void connectedEquipments() {

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices = adapter.getBondedDevices();
        Log.i("devicesNum", devices.size() + "--");

        for (BluetoothDevice device : devices) {
            deviceList.add(device);
            blueToochName = blueToochName + device.getName() + "\n";
        }


    }


    private void connect(BluetoothDevice device)  {
        // 固定的UUID
        final String SPP_UUID = "fa87c0d0-afac-11de-8a39-0800200c9a66";
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket = null;
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
        } catch (IOException e) {
            Log.i("connect","connect::"+e.toString());
            e.printStackTrace();
        }

    }


    /**
     * 与设备配对 参考源码：platform/packages/apps/Settings.git
     * /Settings/src/com/android/settings/bluetooth/CachedBluetoothDevice.java
     */
    static public boolean createBond(Class btClass, BluetoothDevice btDevice)
            throws Exception {
        Method createBondMethod = btClass.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    /**
     * 与设备解除配对 参考源码：platform/packages/apps/Settings.git
     * /Settings/src/com/android/settings/bluetooth/CachedBluetoothDevice.java
     */
    static public boolean removeBond(Class btClass, BluetoothDevice btDevice)
            throws Exception {
        Method removeBondMethod = btClass.getMethod("removeBond");
        Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    // 取消用户输入
    static public boolean cancelPairingUserInput(Class btClass, BluetoothDevice device) throws Exception {
        Method createBondMethod = btClass.getMethod("cancelPairingUserInput");
        // cancelBondProcess()
        Boolean returnValue = (Boolean) createBondMethod.invoke(device);
        return returnValue.booleanValue();
    }

    // 取消配对
    static public boolean cancelBondProcess(Class btClass, BluetoothDevice device) throws Exception {
        Method createBondMethod = btClass.getMethod("cancelBondProcess");
        Boolean returnValue = (Boolean) createBondMethod.invoke(device);
        return returnValue.booleanValue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
