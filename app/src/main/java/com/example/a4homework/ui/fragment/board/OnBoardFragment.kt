package com.example.a4homework.ui.fragment.board

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.session.MediaSession.Token
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentOnBoardBinding
import com.example.a4homework.ui.App
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate) {

    private val adapter : BoardAdapter by lazy {BoardAdapter(this::listener)}
    private lateinit var  auth : FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient


    override fun setupUI() {
        binding.pager.adapter = adapter
        initGoogleClient()

        TabLayoutMediator(binding.tabLayout, binding.pager){
            tab: TabLayout.Tab , position: Int -> tab.setIcon(R.drawable.tab_indicator_background)
        }.attach()
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
                    findNavController().navigateUp()
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
    private fun listener(){
        App.prefs.safeBoardState()
//        singIn()
        findNavController().navigate(R.id.phoneFragment)
    }
}



