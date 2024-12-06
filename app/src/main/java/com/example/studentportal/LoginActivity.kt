package com.example.studentportal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    var lgnname:EditText?=null
    var lgnpassword:EditText?=null
    var lgnbtn:Button?=null
    var regbtn:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        lgnname=findViewById(R.id.edt_name)
        lgnpassword=findViewById(R.id.edt_password)
        lgnbtn=findViewById(R.id.btn_login)
        regbtn=findViewById(R.id.lgn_btn)

        lgnbtn?.setOnClickListener{
            var name=lgnname?.text.toString()
            var password=lgnpassword?.text.toString()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both name and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val pref=getSharedPreferences("myprefs",Context.MODE_PRIVATE)
            val savedname=pref.getString("Student Name",null)
            val savedpassword=pref.getString("Student Password",null)
            val savedbranch=pref.getString("Selected Branch",null)

            if(name==savedname && password==savedpassword) {
                val editor = pref.edit()
                editor.putBoolean("isLoggedIn", true) // Store the login status
                editor.apply()

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", savedname)
                intent.putExtra("Selected Branch",savedbranch)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account not found or incorrect details", Toast.LENGTH_SHORT).show()
            }
        }
        regbtn?.setOnClickListener {
            // Move to RegistrationActivity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}