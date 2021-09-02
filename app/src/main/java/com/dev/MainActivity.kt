package com.dev

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dev.manager.PluginManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PluginManager.loadPlugin(this)
    }

    fun rooter(view: View) {
        startActivity(Intent(this, ProxyActivity::class.java).apply {
            putExtra("className", PluginManager.getPackageInfo()?.activities?.get(0)?.name)
        })
    }
}