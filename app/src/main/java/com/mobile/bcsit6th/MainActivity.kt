package com.mobile.bcsit6th

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
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

//    private var myService: MyService? = null
//    private var isBound = false
//
//    // Service Connection (Binding)
//    private val connection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            val binder = service as MyService.MyBinder
//            myService = binder.getService()
//            isBound = true
//            Log.d("MainActivity", "Service Connected")
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//            isBound = false
//        }
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (isBound) {
//            unbindService(connection)
//        }
//    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        val btnStartService = findViewById<Button>(R.id.btnStartService)
//        val btnThread = findViewById<Button>(R.id.btnThread)
//        val btnStopService = findViewById<Button>(R.id.btnStopService)
//
//// 🔹 Start Service
//        btnStartService.setOnClickListener {
//            val intent = Intent(this, MyService::class.java)
//            startService(intent)   // this triggers onStartCommand()
//        }
//
//// 🔹 Stop Service
//        btnStopService.setOnClickListener {
//            val intent = Intent(this, MyService::class.java)
//            stopService(intent)   // this triggers onDestroy()
//        }
//
//        // 🔹 Thread Example
//        btnThread.setOnClickListener {
//            Thread {
//                for (i in 1..5) {
//                    Thread.sleep(1000)
//                    Log.d("Thread", "Running: $i")
//                }
//            }.start()
//        }

//        button = findViewById(R.id.btnSignUp)
        drawerlayout =  findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        toolbar = findViewById(R.id.myToolbar)
//        textView= findViewById(R.id.txtPage)

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


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commit()
        }


        navView.setNavigationItemSelectedListener {

            val fragment = when (it.itemId) {

                R.id.menuHome -> {
                    HomeFragment()
                }

                R.id.menuSetting -> {
                    SettingFragment()
                }

                R.id.menuAbout -> {
                    AboutFragment()
                }

                R.id.menuContact -> {
                    ContactFragment()
                }

                R.id.menuFeedback -> {
                    FeedbackFragment()
                }

                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()

            drawerlayout.closeDrawers()
            true
        }



//        button.setOnClickListener {
//            showDialog()
//        }

    }

}