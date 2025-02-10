package com.example.sensorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProximidad = findViewById<Button>(R.id.btnProximidad)
        val btnLuminosidad = findViewById<Button>(R.id.btnLuminosidad)

        btnProximidad.setOnClickListener {
            startActivity(Intent(this, ProximidadActivity::class.java))
        }

        btnLuminosidad.setOnClickListener {
            startActivity(Intent(this, LuminosidadActivity::class.java))
        }
    }
}