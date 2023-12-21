package com.example.task3lab7;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    // Global variables for sensors
    SensorManager sensorManager;
    Sensor accelerometerSensor;
    Sensor gyroscopeSensor;
    Sensor magnetometerSensor;

    // Declare TextViews for the 3 axes of each sensor

    //Accelerometer axes textviews
    TextView xAxisAccelerometerTextView;
    TextView yAxisAccelerometerTextView;
    TextView zAxisAccelerometerTextView;

    //Gyroscope axes textviews
    TextView xAxisGyroscopeTextView;
    TextView yAxisGyroscopeTextView;
    TextView zAxisGyroscopeTextView;

    //Magnetometer axes textviews
    TextView xAxisMagnetometerTextView;
    TextView yAxisMagnetometerTextView;
    TextView zAxisMagnetometerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize sensors
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Match views to their resources
        xAxisAccelerometerTextView = findViewById(R.id.xAxisAccelerometerTextView);
        yAxisAccelerometerTextView = findViewById(R.id.yAxisAccelerometerTextView);
        zAxisAccelerometerTextView = findViewById(R.id.zAxisAccelerometerTextView);

        xAxisGyroscopeTextView = findViewById(R.id.xAxisGyroscopeTextView);
        yAxisGyroscopeTextView = findViewById(R.id.yAxisGyroscopeTextView);
        zAxisGyroscopeTextView = findViewById(R.id.zAxisGyroscopeTextView);

        xAxisMagnetometerTextView = findViewById(R.id.xAxisMagnetometerTextView);
        yAxisMagnetometerTextView = findViewById(R.id.yAxisMagnetometerTextView);
        zAxisMagnetometerTextView = findViewById(R.id.zAxisMagnetometerTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register sensor listeners
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister sensor listeners to save power
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                displayAccelerometerValues(event.values);
            case Sensor.TYPE_GYROSCOPE:
                displayGyroscopeValues(event.values);
            case Sensor.TYPE_MAGNETIC_FIELD:
                displayMagnetometerValues(event.values);
                break;

            default:
                // unknown sensor type
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nothing here
    }

    private void displayAccelerometerValues(float[] values) {
        xAxisAccelerometerTextView.setText("Accelerometer X-axis: " + values[0]);
        yAxisAccelerometerTextView.setText("Accelerometer Y-axis: " + values[1]);
        zAxisAccelerometerTextView.setText("Accelerometer Z-axis: " + values[2]);
    }

    private void displayGyroscopeValues(float[] values) {
        xAxisGyroscopeTextView.setText("Gyroscope X-axis: " + values[0]);
        yAxisGyroscopeTextView.setText("Gyroscope Y-axis: " + values[1]);
        zAxisGyroscopeTextView.setText("Gyroscope Z-axis: " + values[2]);
    }

    private void displayMagnetometerValues(float[] values) {
        xAxisMagnetometerTextView.setText("Magnetometer X-axis: " + values[0]);
        yAxisMagnetometerTextView.setText("Magnetometer Y-axis: " + values[1]);
        zAxisMagnetometerTextView.setText("Magnetometer Z-axis: " + values[2]);
    }
}

