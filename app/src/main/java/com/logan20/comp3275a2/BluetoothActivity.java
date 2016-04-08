package com.logan20.comp3275a2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    //ArrayList<String> deviceList = new ArrayList<>();
    BluetoothAdapter bluetoothAdapter;
    ArrayAdapter<String> devicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        devicesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView lv = (ListView)findViewById(R.id.device_view);
        if (lv != null) {
            lv.setAdapter(devicesAdapter);
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_UUID);
        this.registerReceiver(broadcastReceiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(broadcastReceiver, filter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBtStatus();
    }

    private void checkBtStatus(){
        if(bluetoothAdapter == null){
            Toast.makeText(this,"Device does not support bluetooth", Toast.LENGTH_SHORT).show();
        }
        else{
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            else {
                bluetoothAdapter.startDiscovery();
            }
        }
    }
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                devicesAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };
}

