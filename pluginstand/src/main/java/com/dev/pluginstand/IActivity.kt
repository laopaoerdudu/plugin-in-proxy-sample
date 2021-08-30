package com.dev.pluginstand

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent

interface IActivity {
    fun attach(proxyActivity: Activity)
    fun onCreate(saveInstanceState: Bundle)
    fun onStart()
    fun onResume()
    fun onPause()
    fun onSaveInstanceState(outState: Bundle)
    fun onStop()
    fun onDestroy()
    fun onTouchEvent(event: MotionEvent)
    fun onBackPressed()
}