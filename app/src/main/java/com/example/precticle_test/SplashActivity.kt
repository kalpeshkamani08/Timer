package com.example.precticle_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.precticle_test.activity.DisplayDataActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        postDelayedMethod()
    }

    private fun postDelayedMethod() {
        Handler().postDelayed({
            intentFun()
        }, SPLASH_TIME_OUT.toLong())
    }

    private fun intentFun() {
         val intent = Intent(applicationContext, DisplayDataActivity::class.java)
        startActivity(intent)
    }
}