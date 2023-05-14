package com.example.a4homework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseFragment<VB: ViewBinding>(
 private val inflate: Inflate<VB>
) : Fragment(){

      private val _binding: VB? = null
      val binding get() = _binding!!



 override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
      ): View? {
       return super.onCreateView(inflater, container, savedInstanceState)
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       setupUI()
       setupObserver()
      }
      abstract fun setupUI()
      open fun setupObserver(){
      }
}