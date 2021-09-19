package com.dev.taopiaopiao

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button

class TaoMainActivity : BaseActivity() {

    // 重写 BaseActivity 中的 onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 调用 BaseActivity 的 setContentView 方法
        setContentView(R.layout.tao_activity_main)

        // 调用 BaseActivity 的 findViewById 方法
        findViewById<Button>(R.id.ivHome).setOnClickListener {
            // 调用 BaseActivity 的 startActivity 方法
            startActivity(Intent(who, TaoImageActivity::class.java))
        }

        findViewById<Button>(R.id.btnStartService).setOnClickListener {
           startService(Intent(who, TaoService::class.java))
        }

        // 因为是动态广播，所以是先注册，再发送
        findViewById<Button>(R.id.btnRegisterBroadcast).setOnClickListener {
            registerReceiver(TaoReceiver(), IntentFilter().apply {
                addAction("com.dev.taopiaopiao.TaoMainActivity")
            })
        }

        findViewById<Button>(R.id.btnSendBroadcast).setOnClickListener {
            sendBroadcast(Intent().apply {
                action = "com.dev.taopiaopiao.TaoMainActivity"
            })
        }
    }
}