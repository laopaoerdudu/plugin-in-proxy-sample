package com.dev

import android.content.ComponentName
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.manager.PluginManager
import com.dev.pluginstand.IActivity

class ProxyActivity : AppCompatActivity() {
    private var shadowActivity: IActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val className = intent.getStringExtra("className")
        try {
            val target = classLoader?.loadClass(className)
            val constructor = target?.getConstructor(*arrayOf())
            val targetActivity = constructor?.newInstance(*arrayOf())
            shadowActivity = targetActivity as? IActivity
            shadowActivity?.attach(this)
            shadowActivity?.onCreate(Bundle())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        shadowActivity?.onStart()
    }

    override fun onResume() {
        super.onResume()
        shadowActivity?.onResume()
    }

    override fun onStop() {
        super.onStop()
        shadowActivity?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        shadowActivity?.onDestroy()
    }

    override fun startActivity(intent: Intent?) {
        // this -> com.dev.ProxyActivity
        // className -> com.dev.taopiaopiao.TaoImageActivity
        super.startActivity(Intent(this, ProxyActivity::class.java).apply {
            putExtra("className", intent?.getStringExtra("className"))
        })
    }

    override fun startService(serviceIntent: Intent?): ComponentName? {
        return super.startService(Intent(this, ProxyService::class.java).apply {
            putExtra("serviceName", serviceIntent?.getStringExtra("serviceName"))
        })
    }

    override fun getClassLoader(): ClassLoader? = PluginManager.getDexClassLoader()

    override fun getResources(): Resources? = PluginManager.getResources()
}