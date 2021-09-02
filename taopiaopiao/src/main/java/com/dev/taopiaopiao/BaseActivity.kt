package com.dev.taopiaopiao

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.dev.pluginstand.IActivity

@SuppressLint("MissingSuperCall")
open class BaseActivity : Activity(), IActivity {
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
        who?.let {
            it.setContentView(layoutResID)
        } ?: run {
            super.setContentView(layoutResID)
        }
    }

    override fun startActivity(intent: Intent?) {
        who?.startActivity(Intent().apply {
            putExtra("className", intent?.component?.className)
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