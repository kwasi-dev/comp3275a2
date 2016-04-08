package com.logan20.comp3275a2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.mainMenuList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleMenuClick(position);
            }
        });
    }

    private void handleMenuClick(int position) {
        switch (position){
            case 0 :
                Log.d("MENU","First option clicked");
                startActivity(new Intent(this, AccelerometerActivity.class));
                break;
            case 1:
                Log.d("MENU", "Second option clicked");
                startActivity(new Intent(this, GyroscopeActivity.class));
                break;
            case 2:
                Log.d("MENU", "Third option clicked");
                startActivity(new Intent(this, ProximityActivity.class));
                break;
            case 3:
                Log.d("MENU", "Fourth option clicked");
                startActivity(new Intent(this, RetrieveGPS.class));
                break;
            case 4:
                Log.d("MENU", "Fifth option clicked");
                startActivity(new Intent(this, BluetoothActivity.class));
                break;
            case 5:
                startActivity(new Intent(this,BootTimeLocationActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, AllStoredLocations.class));
                break;
            default:
                Log.d("INVALID OPTION","Invalid Option Selected");
                break;

        }
    }

}
