package com.example.a4homework.ui.fragment.board

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentOnBoardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate) {

    private val adapter : BoardAdapter by lazy {BoardAdapter(this::listener)}
    override fun setupUI() {
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.pager){
            tab: TabLayout.Tab , position: Int -> tab.setIcon(R.drawable.tab_indicator_background)
        }.attach()


    }
    private fun listener(){
        findNavController().navigate(R.id.noteFragment2)
    }
}



