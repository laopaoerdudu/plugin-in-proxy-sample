package com.dev.pluginstand

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent

interface IActivity {
    fun attach(proxyActivity: Activity?)
    fun onCreate(savedInstanceState: Bundle?)
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onSaveInstanceState(outState: Bundle)
    fun onDestroy()
}