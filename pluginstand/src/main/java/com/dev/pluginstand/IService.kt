package com.dev.pluginstand

import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.IBinder

interface IService {
    fun attach(service: Service)
    fun onCreate()
    fun onStart(intent: Intent, startId: Int)
    fun onStartCommand(intent: Intent, flags: Int, startId: Int)
    fun onConfigurationChanged(newConfig: Configuration)
    fun onBind(intent: Intent): IBinder
    fun onRebind(intent: Intent)
    fun onUnbind(intent: Intent): Boolean
    fun onTaskRemoved(intent: Intent)
    fun onLowMemory()
    fun onTrimMemory(level: Int)
    fun onDestroy()
}