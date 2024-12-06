package com.example.studentportal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    var screentimer: Long=2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedpref=getSharedPreferences("myprefs",Context.MODE_PRIVATE)
        val isLoggedIn=sharedpref.getBoolean("isLoggedIn",false)

        Handler(Looper.getMainLooper()).postDelayed({

            if (isLoggedIn) {
                // If logged in, redirect to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                // If not logged in, redirect to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        },screentimer)
    }
}