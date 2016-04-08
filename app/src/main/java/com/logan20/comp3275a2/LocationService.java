package com.logan20.comp3275a2;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by kwasi on 4/8/2016.
 */
public class LocationService extends IntentService implements CloseListener {
    private static final String NAME = "DCIT-LocationService";
    protected LocationManager manager;
    protected LocationListener listener;
    private boolean hasPermission = false;

    @Override
    public void deRegisterListener() {
        if (manager!=null &&listener!=null){
            if (hasPermission)
                manager.removeUpdates(listener);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new logan20LocationListener(this,this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Log.d(NAME, "Unable to access GPS");
        }
        else {
            hasPermission=true;
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500,5,listener);
        }
    }
}
