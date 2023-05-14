package com.example.a4homework.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.a4homework.R
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

        Handler(Looper.getMainLooper()).postDelayed({
            if(progres >= 1500){
                startApp()
                finish()
            }else{
                start()
                progres+=100
                binding.LaunchProgress.progress = progres
            }
        },100)
    }
    private fun startApp(){
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
    }
}