package com.ayon.navdrawersensor;

import android.graphics.Color;
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

public class ProximityFragment extends Fragment implements SensorEventListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_proximity,null);
    }

    private Sensor mySensor;
    private SensorManager SM;

    private TextView x,y,z,a;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SM = (SensorManager) getActivity().getSystemService((SENSOR_SERVICE));
        /*Proximity*/
        mySensor = SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*Proximity*/
        if(sensorEvent.values[0] < mySensor.getMaximumRange()){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        } else {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        SM.unregisterListener(this);
    }
}
