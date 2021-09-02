package com.dev

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dev.manager.PluginManager
import com.dev.pluginstand.IActivity

// 代理类其实就是插件的副本
// ClassLoader, Resources 都是插件的
class ProxyActivity : AppCompatActivity() {
    private lateinit var shadowActivity: IActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val className = intent.getStringExtra("className")
        try {
            val target = classLoader?.loadClass(className)
            val constructor = target?.getConstructor(*arrayOf())
            val targetActivity = constructor?.newInstance(*arrayOf())
            shadowActivity = targetActivity as IActivity
            shadowActivity.attach(this)
            shadowActivity.onCreate(Bundle())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        shadowActivity.onStart()
    }

    override fun onResume() {
        super.onResume()
        shadowActivity.onResume()
    }

    override fun onStop() {
        super.onStop()
        shadowActivity.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        shadowActivity.onDestroy()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(Intent(this, ProxyActivity::class.java).apply {
            // className -> com.dev.taopiaopiao.ImageActivity
            putExtra("className", intent?.getStringExtra("className"))
        })
    }

    override fun getClassLoader(): ClassLoader? = PluginManager.getDexClassLoader()

    override fun getResources(): Resources? = PluginManager.getResources()
}