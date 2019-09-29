package com.ayon.navdrawersensor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*SENSOR IMPORTS*/
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;

public class GyroscopeFragment extends Fragment implements SensorEventListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_gyroscope,null);
    }

    private Sensor mySensor;
    private SensorManager SM;


    private TextView x,y,z;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        x = view.findViewById(R.id.x);
        y = view.findViewById(R.id.y);
        z = view.findViewById(R.id.z);

        SM = (SensorManager) getActivity().getSystemService((SENSOR_SERVICE));

        /*Gyroscope*/
        mySensor = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*Gyroscope*/
        x.setText("X-axis: " + sensorEvent.values[0]);
        y.setText("Y-axis: " + sensorEvent.values[1]);
        z.setText("Z-axis: " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onResume() {
        super.onResume();
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }
}
