package com.logan20.comp3275a2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener{
    SensorManager sm;
    Sensor prox;
    TextView prx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        init();
        setSensor();
        registerListeners();
    }
    private void setSensor() {
        prox = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onStop(){
        finish();
        super.onStop();
    }

    public void registerListeners(){
        sm.registerListener(this, prox, SensorManager.SENSOR_DELAY_FASTEST);
        if (prox==null)
            Toast.makeText(this, "This device doesn't have a proximity sensor", Toast.LENGTH_LONG).show();
    }
    private void init() {
        sm=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        prx=(TextView)findViewById(R.id.idProxVal);
    }

    private void unregisterListener() {
        sm.unregisterListener(this,prox);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            prx.setText("Proximity value: "+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

}
