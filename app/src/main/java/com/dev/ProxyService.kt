package com.dev

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dev.manager.PluginManager
import com.dev.pluginstand.IService

class ProxyService : Service() {
    private var shadowService: IService? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(shadowService == null) {
            setup(intent)
        }
        return shadowService?.onStartCommand(intent, flags, startId) ?: 0
    }

    override fun onBind(intent: Intent?): IBinder? {
        setup(intent)
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return shadowService?.onUnbind(intent) ?: false
    }

    override fun getClassLoader(): ClassLoader? = PluginManager.getDexClassLoader()

    private fun setup(intent: Intent?) {
        val serviceName = intent?.getStringExtra("serviceName")
        try {
            val target = classLoader?.loadClass(serviceName)
            val constructor = target?.getConstructor(*arrayOf())
            val targetService = constructor?.newInstance(*arrayOf())
            shadowService = targetService as? IService
            shadowService?.attach(this)
            shadowService?.onCreate()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}