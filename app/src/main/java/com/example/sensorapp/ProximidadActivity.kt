package com.example.sensorapp

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ProximidadActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    private lateinit var valorText: TextView
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximidad)

        valorText = findViewById(R.id.txtValor)
        mainLayout = findViewById(R.id.mainLayout)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_PROXIMITY) {
                valorText.text = "VALOR: ${it.values[0]}"

                if (it.values[0] == 0f) {  // Objeto cerca
                    mainLayout.setBackgroundColor(Color.RED)
                    valorText.setBackgroundColor(Color.BLUE)
                    valorText.setTextColor(Color.WHITE)
                } else {  // Objeto lejos
                    mainLayout.setBackgroundColor(Color.WHITE)
                    valorText.setBackgroundColor(Color.TRANSPARENT)
                    valorText.setTextColor(Color.BLACK)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
