package com.dev.taopiaopiao

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView

class MainActivity : BaseActivity() {

    // 重写 BaseActivity 中的 onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 调用 BaseActivity 的 setContentView 方法
        // 其实最终调用的是 ProxyActivity 的 setContentView 方法
        setContentView(R.layout.activity_main)

        // 调用 BaseActivity 的 findViewById 方法
        findViewById<ImageView>(R.id.ivHome).setOnClickListener {

            // 调用 BaseActivity 的 startActivity 方法
            startActivity(Intent(who, ImageActivity::class.java))
        }
    }
}