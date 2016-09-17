package com.example.dise07.sensoresproximidad;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ln=(LinearLayout) findViewById(R.id.Linear);
        tv=(TextView) findViewById(R.id.texto);
        sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String texto = String.valueOf(event.values[0]);
        tv.setText(texto);

        float valor=Float.parseFloat(texto);
        if(valor==3.0){
            tv.setText("" + valor);
            ln.setBackgroundColor(Color.BLUE);
        }else{
            if (valor==1.0){
                tv.setText("" + valor);
                ln.setBackgroundColor(Color.GREEN);
            }else{
                tv.setText("" + valor);
                ln.setBackgroundColor(Color.YELLOW);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
