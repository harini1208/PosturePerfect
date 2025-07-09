package com.example.postureapp

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class DashboardActivity : AppCompatActivity() {

    private lateinit var flexText: TextView
    private lateinit var gyroText: TextView
    private lateinit var statusText: TextView

    private val handler = Handler()
    private val updateInterval = 2000L // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        flexText = findViewById(R.id.flexValue)
        gyroText = findViewById(R.id.gyroValue)
        statusText = findViewById(R.id.postureStatus)

        simulateSensorData()
    }

    private fun simulateSensorData() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val flexValue = Random.nextInt(0, 100)
                val gyroValue = Random.nextInt(-90, 90)

                flexText.text = "Flex Sensor: $flexValue"
                gyroText.text = "Gyroscope: $gyroValue"

                val isPostureCorrect = isPostureGood(flexValue, gyroValue)
                statusText.text = "Posture Status: ${if (isPostureCorrect) "Correct ✅" else "Incorrect ❌"}"
                statusText.setTextColor(if (isPostureCorrect) 0xFF388E3C.toInt() else 0xFFD32F2F.toInt())

                handler.postDelayed(this, updateInterval)
            }
        }, updateInterval)
    }

    private fun isPostureGood(flex: Int, gyro: Int): Boolean {
        // Define your threshold logic here
        return flex in 30..60 && gyro in -10..10
    }
}
