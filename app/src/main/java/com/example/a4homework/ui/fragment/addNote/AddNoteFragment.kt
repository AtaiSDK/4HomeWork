package com.example.a4homework.ui.fragment.addNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.data.model.NoteModel
import com.example.a4homework.databinding.FragmentAddNoteBinding
import com.example.a4homework.ui.App
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    private lateinit var db: FirebaseFirestore
    private val note: MutableMap<String, Any> = HashMap()


    override fun setupUI() {
        db = FirebaseFirestore.getInstance()
        if (arguments != null) {
            initFirestore()
        } else {
            initRoom()
        }
    }

    private fun initRoom() {
        binding.btnAdd.setOnClickListener {
            val title = binding.edTitle.text.toString()
            val desc = binding.edDesc.text.toString()

            val formatter = DateTimeFormatter.ofPattern("HH:mm | dd:MM:yyyy")
            val date = LocalDateTime.now().format(formatter)

            App.db.getNoteDao().addNote(NoteModel(title = title, desc = desc, date = date))
            findNavController().navigateUp()
        }
    }

    private fun initFirestore() {
        binding.btnAdd.setOnClickListener {
            val title = binding.edTitle.text.toString()
            val desc = binding.edDesc.text.toString()

            val formatter = DateTimeFormatter.ofPattern("HH:mm | dd:MM:yyyy")
            val date = LocalDateTime.now().format(formatter)
            note["title"] = title
            note["desc"] = desc
            note["date"] = date

            db.collection("note").add(note).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigateUp()
                    findNavController().navigate(R.id.firestoreFragment)
                } else {
                    Toast.makeText(requireContext(), "что-то пошло не так", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                    findNavController().navigate(R.id.firestoreFragment)
                }
            }
        }
    }
}