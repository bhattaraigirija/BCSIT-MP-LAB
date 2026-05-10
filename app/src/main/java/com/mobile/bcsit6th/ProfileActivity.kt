package com.mobile.bcsit6th

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txvName = findViewById<TextView>(R.id.txvName)
        val txvAddress = findViewById<TextView>(R.id.txvAddress)
        val txvEmail = findViewById<TextView>(R.id.txvEmail)
        val txvPhone = findViewById<TextView>(R.id.txvPhone)

        //get data from registration page
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val phone = intent.getStringExtra("phone")
        val gender = intent.getStringExtra("gender")

        //set data to views
        txvName.text = name
        txvAddress.text = address
        txvEmail.text = email
        txvPhone.text = phone
    }
}