package com.example.postureapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class DashboardActivity : AppCompatActivity() {

    private lateinit var flexText: TextView
    private lateinit var gyroXText: TextView
    private lateinit var gyroYText: TextView
    private lateinit var gyroZText: TextView
    private lateinit var statusText: TextView



    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 2000L // 2 seconds


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        flexText = findViewById(R.id.flexValue)
        gyroXText = findViewById(R.id.gyroXValue)
        gyroYText = findViewById(R.id.gyroYValue)
        gyroZText = findViewById(R.id.gyroZValue)
        statusText = findViewById(R.id.postureStatus)

        simulateSensorData() // ✅ Starts the auto-generation loop
    }


    private fun simulateSensorData() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val flexValue = Random.nextInt(0, 100)
                val gyroXValue = Random.nextInt(-180, 180)
                val gyroYValue = Random.nextInt(-180, 180)
                val gyroZValue = Random.nextInt(-180, 180)

                flexText.text = "Flex Sensor: $flexValue"
                gyroXText.text = "Gyroscope X: $gyroXValue"
                gyroYText.text = "Gyroscope Y: $gyroYValue"
                gyroZText.text = "Gyroscope Z: $gyroZValue"

                val isPostureCorrect = isPostureGood(flexValue, gyroXValue, gyroYValue, gyroZValue)
                statusText.text = "Posture Status: ${if (isPostureCorrect) "Correct ✅" else "Incorrect ❌"}"
                statusText.setTextColor(
                    if (isPostureCorrect) 0xFF388E3C.toInt() else 0xFFD32F2F.toInt()
                )

                handler.postDelayed(this, updateInterval)
            }
        }, updateInterval)
    }

    private fun isPostureGood(flex: Int, gyroX: Int, gyroY: Int, gyroZ: Int): Boolean {
        return flex in 30..60 &&
                gyroX in -10..10 &&
                gyroY in -10..10 &&
                gyroZ in -10..10
    }
}

