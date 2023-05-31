package com.example.a4homework.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4homework.R
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentNoteBinding
import com.example.a4homework.databinding.FragmentOnBoardBinding
import com.example.a4homework.ui.App

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

    private val adapter: NoteAdapter by lazy {
        NoteAdapter()
    }

    override fun setupUI() {
        val models = App.db.getNoteDao().getAllNote()

        adapter.setList(models)
        binding.rvNote.adapter = adapter
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
    }
}