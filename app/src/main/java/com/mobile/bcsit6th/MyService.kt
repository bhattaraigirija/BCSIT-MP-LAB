package com.mobile.bcsit6th

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService", "Service is Running")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("MyService", "Service Bound")
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "Service is Destroyed")
    }
}