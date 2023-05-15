package com.example.a4homework.ui.fragment.board

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentOnBoardBinding


class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate) {

    private val adapter : BoardAdapter by lazy {BoardAdapter(this::listener)}
    override fun setupUI() {
        binding.pager.adapter = adapter
    }
    private fun listener(){
        findNavController().navigate(R.id.noteFragment2)
    }
}



