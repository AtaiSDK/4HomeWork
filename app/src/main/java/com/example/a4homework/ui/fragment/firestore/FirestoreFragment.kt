package com.example.a4homework.ui.fragment.firestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentFirestoreBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class FirestoreFragment : BaseFragment<FragmentFirestoreBinding>(FragmentFirestoreBinding::inflate) {

    private val adapter: FireAdapter by lazy {
        FireAdapter()
    }
    private lateinit var db: FirebaseFirestore
    private val note: MutableMap<String, Any> = HashMap()
    private val list = ArrayList<FireModel>()



    override fun setupUI() {
        db = FirebaseFirestore.getInstance()
        binding.rvNote.adapter = adapter
        binding.btnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", "id")
            findNavController().navigateUp()
            findNavController().navigate(R.id.addNoteFragment, bundle)
        }


        db.collection("note").get().addOnSuccessListener {
            for (document in it) {
                val notes = document.toObject(FireModel::class.java)
                list.add(notes)
                adapter.setList(list)
                list.clear()
            }
        }
            .addOnFailureListener {
                Log.e("ololo", "error")
            }



    }

}