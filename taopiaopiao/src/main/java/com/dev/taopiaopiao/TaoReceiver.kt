package com.dev.taopiaopiao

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.dev.pluginstand.IBroadcast

class TaoReceiver : BroadcastReceiver(), IBroadcast {
    override fun attach(context: Context) {
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("WWE", "TaoReceiver #onReceive -> context -> ${context?.javaClass?.name}")
    }
}