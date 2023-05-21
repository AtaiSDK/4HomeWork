package com.example.a4homework.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.a4homework.R
import com.example.a4homework.databinding.ActivityMainBinding
import com.example.a4homework.ui.App

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        controller = navHostFragment.navController

        if(!App.prefs.isBoardShow()){
            controller.navigate(R.id.onBoardFragment2)
        }

        controller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.noteFragment2 ||
                destination.id == R.id.addNoteFragment ||
                destination.id == R.id.profileFragment
            ){
                binding.bottomNavMenu.visibility = View.VISIBLE
            }else{
                binding.bottomNavMenu.visibility = View.GONE
            }


            binding.bottomNavMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 ->{
                    controller.navigate(R.id.noteFragment2)
                }
                R.id.item2 ->{
                    controller.navigate(R.id.addNoteFragment)
                }
                R.id.item3 ->{
                    controller.navigate(R.id.profileFragment)
                }
            }
            true
        }
    }
}}