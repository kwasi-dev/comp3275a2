package com.logan20.comp3275a2;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by kwasi on 4/8/2016.
 */
public class logan20 implements LocationListener {
    private final Context context;
    private final CloseListener closer;
    public PrefLocationListener(Context context, CloseListener closer){
        this.context = context;
        this.closer = closer;
    }
    @Override
    public void onLocationChanged(Location location) {
        SharedPreferences sp = context.getSharedPreferences("DCIT",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        String rep = sp.getString("last_loc",null);

        if (rep!=null){
            Location oldLoc = convertFromRep(rep);
            float dist = location.distanceTo(oldLoc);
            dist+=sp.getFloat("total_dist",0);


            ed.putFloat("total_dist",dist);
        }

        ed.putString("last_loc",convert2Rep(location));

        ed.commit();

        if (closer!=null){
            closer.deRegisterListener();
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

    private  String convert2Rep(Location loc){
        return loc.getLatitude()+","+loc.getLongitude()+","+loc.getAltitude();
    }
    private Location convertFromRep(String loc){
        String[] res = loc.split(",");
        double lat = Double.parseDouble(res[0]);
        double lon = Double.parseDouble(res[1]);
        double alt = Double.parseDouble(res[2]);

        Location location = new Location("");
        location.setAltitude(alt);
        location.setLatitude(lat);
        location.setLongitude(lon);
        return location;
    }
}
