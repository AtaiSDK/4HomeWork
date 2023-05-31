package com.example.a4homework.ui.fragment.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentCodeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class CodeFragment : BaseFragment<FragmentCodeBinding>(FragmentCodeBinding::inflate) {

    private lateinit var mAuth: FirebaseAuth

    override fun setupUI() {
        mAuth = FirebaseAuth.getInstance()
        if (arguments != null) {
            val id = arguments?.getString("id").toString()


            binding.btnAdd.setOnClickListener {
                val code = binding.edCode.text.toString()

                val credential = PhoneAuthProvider.getCredential(id, code)

                mAuth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(R.id.noteFragment2)
                    } else {
                        Toast.makeText(requireContext(), "что-то пошло не так", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }

}