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

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    Sensor gyro;
    TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        init();
        setSensor();
        registerListeners();
    }

    private void setSensor() {
        gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    private void init() {
        sm=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        x=(TextView)findViewById(R.id.idGyroX);
        y=(TextView)findViewById(R.id.idGyroY);
        z=(TextView)findViewById(R.id.idGyroZ);
    }
    public void registerListeners(){
        sm.registerListener(this, gyro, SensorManager.SENSOR_DELAY_FASTEST);
        if (gyro==null)
            Toast.makeText(this, "This device doesn't have a gyroscope", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
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
        sm.unregisterListener(this, gyro);
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
