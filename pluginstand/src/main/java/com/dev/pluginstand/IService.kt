package com.dev.pluginstand

import android.app.Service
import android.content.Intent
import android.os.IBinder

interface IService {
    fun attach(service: Service)
    fun onCreate()
    fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    fun onBind(intent: Intent?): IBinder?
    fun onUnbind(intent: Intent?): Boolean
}