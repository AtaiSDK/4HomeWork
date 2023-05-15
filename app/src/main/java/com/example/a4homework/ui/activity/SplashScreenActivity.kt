package com.example.a4homework.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.a4homework.databinding.ActivitySplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private var progres = 0
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
    }
    private fun start(){

        object : CountDownTimer(1000, 100) {
            override fun onTick(l: Long) {}
            override fun onFinish() {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            }
        }.start()
    }
}