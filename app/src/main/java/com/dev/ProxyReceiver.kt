package com.dev

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dev.manager.PluginManager
import com.dev.pluginstand.IBroadcast

class ProxyReceiver(className: String?, context: Context) : BroadcastReceiver() {
    private var shadowBroadcastReceiver: IBroadcast? = null

    init {
        try {
            val target = PluginManager.getDexClassLoader()?.loadClass(className)
            val constructor = target?.getConstructor(*arrayOf())
            val targetReceiver = constructor?.newInstance(*arrayOf())
            shadowBroadcastReceiver = targetReceiver as? IBroadcast
            shadowBroadcastReceiver?.attach(context)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        shadowBroadcastReceiver?.onReceive(context, intent)
    }
}