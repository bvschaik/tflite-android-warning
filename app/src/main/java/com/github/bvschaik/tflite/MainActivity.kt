package com.github.bvschaik.tflite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.tflite.gpu.support.TfLiteGpu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.check_button).setOnClickListener {
            TfLiteGpu.isGpuDelegateAvailable(this@MainActivity).addOnSuccessListener { supported ->
                findViewById<TextView>(R.id.result).text = "GPU support: $supported"
            }
        }
    }
}
