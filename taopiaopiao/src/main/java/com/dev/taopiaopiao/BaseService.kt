package com.dev.taopiaopiao

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dev.pluginstand.IService

@SuppressLint("MissingSuperCall")
open class BaseService : Service(), IService {
    private lateinit var who: Service

    override fun attach(service: Service) {
        this.who = service
    }

    override fun onCreate() {}

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}