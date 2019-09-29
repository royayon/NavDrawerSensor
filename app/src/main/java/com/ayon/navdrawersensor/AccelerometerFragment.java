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


public class AccelerometerFragment extends Fragment implements SensorEventListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_accelerometer,null);
    }

    private Sensor mySensor;
    private SensorManager SM;


    private TextView x,y,z,a;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        x = view.findViewById(R.id.x);
        y = view.findViewById(R.id.y);
        z = view.findViewById(R.id.z);

        a = view.findViewById(R.id.a);

        SM = (SensorManager) getActivity().getSystemService((SENSOR_SERVICE));

        /*Accelerometer*/
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*Accelerometer*/
        x.setText("X-axis: " + sensorEvent.values[0]);
        y.setText("Y-axis: " + sensorEvent.values[1]);
        z.setText("Z-axis: " + sensorEvent.values[2]);

        Float X = sensorEvent.values[0];
        Float Y = sensorEvent.values[1];
        Float Z = sensorEvent.values[2];

        if(X > 8.0){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            a.setText("X-axis");
        } else if(X < -8.0){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            a.setText("X-axis");
        } else if(Y > 8.0){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            a.setText("Y-axis");
        } else if(Y < -8.0){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.RED);
            a.setText("Y-axis");
        } else if(Z > 8.0){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            a.setText("Z-axis");
        } else if(Z < -8.0){
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            a.setText("Z-axis");
        } else {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            a.setText("NO-axis");
        }
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
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        SM.unregisterListener(this);
    }
}
