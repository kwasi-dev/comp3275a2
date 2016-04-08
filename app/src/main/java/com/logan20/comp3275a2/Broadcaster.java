package com.logan20.comp3275a2;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by kwasi on 4/8/2016.
 */
public class Broadcaster extends BroadcastReceiver {
    public static final int INTERVAL = 5000;
    public static final int REQUEST_CODE = 342;

    @Override
    public void onReceive(Context context, Intent intent) {
        setUpService(context);
    }

    public static boolean setUpService(final Context context){
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent (context,BootTimeLocationActivity.class);
        PendingIntent pi = PendingIntent.getService(context,REQUEST_CODE,i, 0);

        return true;
    }
}
