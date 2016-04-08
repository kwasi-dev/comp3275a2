package com.logan20.comp3275a2;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

@SuppressWarnings("ALL")
public class BootTimeLocationActivity extends AppCompatActivity implements LocationListener {
    private static final int MY_CODE = 234;
    private LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_time_location);
        if (!checkPerm()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_CODE);
        } else {
            init();
        }
    }

    public void deRegisterListener() {
        if (manager != null ) {
            manager.removeUpdates(this);
            Log.d("DEREG","Listener is deregistered");
        }
    }
    private void init() {
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500,5,this);

    }

    private boolean checkPerm() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onLocationChanged(Location location) {
        //database code to save to database
        SQLiteOpenHelper helper = new DBHelper(this);
        final SQLiteDatabase db =helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put (GPS_Contract.GPSEntry.LATITUDE,location.getLatitude()+"");
        cv.put (GPS_Contract.GPSEntry.LONGITUDE,location.getLongitude()+"");
        cv.put (GPS_Contract.GPSEntry.ALTITUDE,location.getAltitude()+"");

        final long cartId = db.insert(GPS_Contract.GPSEntry.TABLE_NAME,null,cv);

        if (cartId!=-1){
            deRegisterListener();
            Log.d("INSERTION", "Successfully added to database");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
