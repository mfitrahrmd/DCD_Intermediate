package com.mfitrahrmd.dcd_intermediate

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mfitrahrmd.dcd_intermediate.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityServiceBinding
    private var isBackgroundServiceRunning: Boolean = false
    private var isForegroundServiceRunning: Boolean = false
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (!isGranted) {
            Toast.makeText(
                this,
                "Unable to display foreground service notification due to permission decline",
                Toast.LENGTH_LONG
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        with(viewBinding) {
            val backgroundServiceIntent = Intent(this@ServiceActivity, MyBackgroundService::class.java)
            val foregroundServiceIntent = Intent(this@ServiceActivity, MyForegroundService::class.java)
            btnBackgroundService.setOnClickListener {
                if (isBackgroundServiceRunning) {
                    stopService(backgroundServiceIntent)
                    isBackgroundServiceRunning = false
                } else {
                    startService(backgroundServiceIntent)
                    isBackgroundServiceRunning = true
                }
            }
            btnForegroundService.setOnClickListener {
                if (isForegroundServiceRunning) {
                    stopService(foregroundServiceIntent)
                    isForegroundServiceRunning = false
                } else {
                    if (Build.VERSION.SDK_INT >= 26) {
                        startForegroundService(foregroundServiceIntent)
                    }else {
                        startService(foregroundServiceIntent)
                    }
                    isForegroundServiceRunning = true
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this@ServiceActivity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}