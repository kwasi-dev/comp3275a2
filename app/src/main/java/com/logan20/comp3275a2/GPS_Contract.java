package com.logan20.comp3275a2;

import android.provider.BaseColumns;

/**
 * Created by kwasi on 4/7/2016.
 */
public final class GPS_Contract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String DATE_TYPE = " DATETIME";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + GPSEntry.TABLE_NAME + "(" +
                    GPSEntry._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    GPSEntry.LATITUDE + TEXT_TYPE + " NOT NULL UNIQUE, " +
                    GPSEntry.LONGITUDE + TEXT_TYPE + " NOT NULL UNIQUE, " +
                    GPSEntry.ALTITUDE + TEXT_TYPE + " NOT NULL UNIQUE, " +
                    GPSEntry.DATEADDED + DATE_TYPE + " DEFAULT CURRENT_TIMESTAMP " +
                    ");";

    public static abstract class GPSEntry implements BaseColumns {
        public static final String TABLE_NAME = "GPS_Locations";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String ALTITUDE = "altitude";
        public static final String DATEADDED = "date_added";

    }
}
