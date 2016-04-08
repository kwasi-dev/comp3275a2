package com.logan20.comp3275a2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllStoredLocations extends AppCompatActivity {
    private ListView lv;
    private ArrayList<String> arrList;
    protected ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stored_locations);
        init();
        populateFromDB(this);
    }

    private void populateFromDB(final Context ctx) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteOpenHelper helper = new DBHelper(ctx);
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor x = db.query(GPS_Contract.GPSEntry.TABLE_NAME,
                        new String[]{
                                GPS_Contract.GPSEntry.LATITUDE,
                                GPS_Contract.GPSEntry.LONGITUDE,
                                GPS_Contract.GPSEntry.ALTITUDE
                        },
                        null,null,null,null,null);

                while (x.moveToNext()){
                    String c ="";
                    c+= "Long: "+(x.getString(x.getColumnIndex(GPS_Contract.GPSEntry.LONGITUDE))).substring(0,6);
                    c+= "\tLat: "+x.getString(x.getColumnIndex(GPS_Contract.GPSEntry.LATITUDE)).substring(0,6);
                    c+= "\tAlt: "+x.getString(x.getColumnIndex(GPS_Contract.GPSEntry.ALTITUDE));
                    arrList.add(c);
                }
                lv.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void init() {
        lv=(ListView)findViewById(R.id.lvStoredLocations);
        arrList=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrList);
        lv.setAdapter(adapter);
    }
}
