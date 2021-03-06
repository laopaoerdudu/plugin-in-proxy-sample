package com.dev.taopiaopiao

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dev.pluginstand.IActivity

@SuppressLint("MissingSuperCall")
open class BaseActivity : AppCompatActivity(), IActivity {
    var who: Activity? = null

    override fun attach(proxyActivity: Activity?) {
        who = proxyActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {}

    override fun onStart() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {}

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun onDestroy() {}

    override fun setContentView(layoutResID: Int) {
        // who -> com.dev.ProxyActivity
        who?.let {
            it.setContentView(layoutResID)
        } ?: run {
            super.setContentView(layoutResID)
        }
    }

    override fun startActivity(intent: Intent?) {
        // 最后还是使用了 ProxyActivity 的 startActivity 方法
        who?.startActivity(Intent().apply {
            // className -> com.dev.taopiaopiao.ImageActivity
            putExtra("className", intent?.component?.className)
        })
    }

    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
        return who?.registerReceiver(receiver, filter)
    }

    override fun sendBroadcast(intent: Intent?) {
        who?.sendBroadcast(intent)
    }

    override fun startService(serviceIntent: Intent?): ComponentName? {
        return who?.startService(Intent().apply {
            putExtra("serviceName", serviceIntent?.component?.className);
        })
    }

    override fun <T : View?> findViewById(id: Int): T {
        return who?.let { activity ->
            activity.findViewById(id)
        } ?: run {
            super.findViewById(id)
        }
    }
}