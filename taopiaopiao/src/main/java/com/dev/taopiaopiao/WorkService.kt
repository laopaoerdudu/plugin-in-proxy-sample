package com.dev.taopiaopiao

import android.util.Log
import kotlin.concurrent.thread

class WorkService : BaseService() {
    private var num = 0

    override fun onCreate() {
        super.onCreate()
        Log.i("WWE", "WorkService onCreate() invoked!")
        thread {
            while (true) {
                num++
                try {
                    Thread.sleep(1000)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
            }
        }
    }
}