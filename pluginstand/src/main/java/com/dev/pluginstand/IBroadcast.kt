package com.dev.pluginstand

import android.content.Context
import android.content.Intent

interface IBroadcast {
    fun attach(context: Context)
    fun onReceive(context: Context?, intent: Intent?)
}