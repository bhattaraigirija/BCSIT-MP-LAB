package com.mobile.bcsit6th

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private fun showDialog() {
        val b= AlertDialog.Builder(this)
        b.setTitle("registration")
        b.setMessage("Are you sure?")
        b.setPositiveButton("Yes") { dialog, _ ->
            {
                startActivity(Intent(this, RegistrationPage::class.java))
            }
        }

        b.setNegativeButton("No"){dialog, _ ->{
            //code
        }}
        b.show()
    }

    private lateinit var drawerlayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var textView: TextView
    private lateinit var button: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btnSignUp)
        drawerlayout =  findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        toolbar = findViewById(R.id.myToolbar)
        textView= findViewById(R.id.txtPage)

        setSupportActionBar(toolbar)
        val toogle =
            ActionBarDrawerToggle(
                this,
                drawerlayout,
                toolbar,
                R.string.open,
                R.string.close)

        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    textView.text = "My Home Page"
                    Toast.makeText(this, "Clicked on Home", Toast.LENGTH_SHORT).show()
                }
                R.id.menuSetting -> {
                    textView.text = "My Setting Page"
                    Toast.makeText(this, "Clicked on Setting", Toast.LENGTH_SHORT).show()
                }
                R.id.menuAbout -> {
                    textView.text = "My About Page"
                    Toast.makeText(this, "Clicked on About", Toast.LENGTH_SHORT).show()
                }
                R.id.menuContact -> {
                    textView.text = "My Contact Page"
                    Toast.makeText(this, "Clicked on Contact", Toast.LENGTH_SHORT).show()
                }
                R.id.menuFeedback -> {
                    textView.text = "My Feedback Page"
                    Toast.makeText(this, "Clicked on Feedback", Toast.LENGTH_SHORT).show()
                }
            }

            drawerlayout.closeDrawers()
            true
        }



        button.setOnClickListener {
            showDialog()
        }

    }

}