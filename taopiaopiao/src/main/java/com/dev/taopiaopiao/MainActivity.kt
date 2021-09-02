package com.dev.taopiaopiao

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.ivHome).setOnClickListener {
            startActivity(Intent(who, ImageActivity::class.java))
        }
    }
}