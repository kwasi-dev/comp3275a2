package com.logan20.comp3275a2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sm;
    Sensor acc;
    TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        init();
        setSensor();
        registerListeners();
    }

    private void setSensor() {
        acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void init() {
        sm=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        x=(TextView)findViewById(R.id.idAccelX);
        y=(TextView)findViewById(R.id.idAccelY);
        z=(TextView)findViewById(R.id.idAccelZ);
    }
    public void registerListeners(){
        sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_FASTEST);
        if (acc==null)
            Toast.makeText(this,"This device doesn't have an accelerometer",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            x.setText("X: "+String.format("%.2f",event.values[0]));
            y.setText("Y: "+String.format("%.2f",event.values[1]));
            z.setText("Z: "+String.format("%.2f",event.values[2]));
        }
    }
    @Override
    protected void onPause(){
        unregisterListener();
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        registerListeners();
    }

    private void unregisterListener() {
        sm.unregisterListener(this, acc);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop(){
        finish();
        super.onStop();
    }

}
