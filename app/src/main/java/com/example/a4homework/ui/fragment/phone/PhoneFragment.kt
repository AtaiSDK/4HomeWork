package com.example.a4homework.ui.fragment.phone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentPhoneBinding
import com.example.a4homework.databinding.FragmentProfileBinding
import com.example.a4homework.ui.App
import com.example.a4homework.ui.activity.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class PhoneFragment : BaseFragment<FragmentPhoneBinding>(FragmentPhoneBinding::inflate) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var  auth : FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient

    override fun setupUI() {
        mAuth = FirebaseAuth.getInstance()
        initGoogleClient()
        binding.btnAdd.setOnClickListener {
            val phone = binding.edPhone.text.toString().trim()

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, 60, TimeUnit.SECONDS,
                activity as MainActivity, callback
            )
        }

        binding.btnGoogle.setOnClickListener {
            singIn()
        }

        callback = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                mAuth.signInWithCredential(p0).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = App.prefs.getUserName()
                        if (user == "") {
                            findNavController().navigate(R.id.registerFragment)
                        } else {
                            findNavController().navigate(R.id.noteFragment2)
                        }
                    } else {
                        Log.e("ololo", "error in register")
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.e("ololo", "error in verification" )
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                val bundle = Bundle()
                bundle.putString("id", p0)
                findNavController().navigate(R.id.codeFragment, bundle)
            }

        }

    }


    private fun initGoogleClient(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSingInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth
    }
    companion object{
        private  const val RG_SING_IN = 9001
    }
    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()){ task ->
                if(task.isSuccessful){
                    findNavController().navigate(R.id.noteFragment2)
                }else{
                    Toast.makeText( requireContext(), task.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
    private fun singIn(){
        val intent = googleSingInClient.signInIntent
        startActivityForResult(intent, RG_SING_IN )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RG_SING_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)

            }catch (e : ApiException){
                e.localizedMessage?.let { Log.e("ololo", it) }
            }
        }
    }

}