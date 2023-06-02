package com.example.a4homework.ui.profile

import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentProfileBinding
import com.example.a4homework.ui.App
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    override fun setupUI() {
        showRegisterFragment()
        setTextFromSharedPref()
        logOut()

    }

    private fun logOut() {
        val btnLogOut : Button = requireView().findViewById(R.id.btn_logout)
        btnLogOut.setOnClickListener {
            App.prefs.logOut(requireActivity())
        }
    }

    private fun setTextFromSharedPref() {
        val married = App.prefs.getUserMarried()
        if (married) {
            binding.tvMarried.text = "Married"
        } else {
            binding.tvMarried.text = "not married"
        }
        binding.userName.text = App.prefs.getUserName()
        binding.userSurname.text = App.prefs.getUserSurName()
        binding.tvUserNumber.text = App.prefs.getUserNumber().toString()
    }

    private fun showRegisterFragment() {
        val image = App.prefs.getUserImage()
        binding.imgUser.setImageURI(image.toUri())

        if(App.prefs.getUserName().isEmpty() && App.prefs.getUserSurName().isEmpty()){
            Toast.makeText(requireContext(), "Please log out again", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.registerFragment)
        }else{
            Toast.makeText(requireContext(), "Welcome", Toast.LENGTH_SHORT).show()
        }
    }
}