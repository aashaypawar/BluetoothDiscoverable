package com.example.bluetoothdiscoverable

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOn = findViewById<Button>(R.id.btnOn)
        val btnOff = findViewById<Button>(R.id.btnOFF)
        val btnDisc = findViewById<Button>(R.id.btnDiscoverable)
        val bAdapter = BluetoothAdapter.getDefaultAdapter()

        btnOn.setOnClickListener {
            if (bAdapter == null) {
                Toast.makeText(applicationContext, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show()
            } else {
                if (!bAdapter.isEnabled) {
                    startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1)
                    Toast.makeText(applicationContext, "Bluetooth Turned ON", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnOff.setOnClickListener {
            bAdapter!!.disable()
            Toast.makeText(applicationContext, "Bluetooth Turned OFF", Toast.LENGTH_SHORT).show()
        }
        btnDisc.setOnClickListener {
            if (!bAdapter!!.isDiscovering) {
                startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE), 1)
                Toast.makeText(applicationContext, "Making Device Discoverable", Toast.LENGTH_SHORT).show()
            }
        }
    }
}