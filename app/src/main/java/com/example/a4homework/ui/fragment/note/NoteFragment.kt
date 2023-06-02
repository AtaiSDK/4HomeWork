package com.example.a4homework.ui.fragment.note

import android.app.AlertDialog
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
import com.example.a4homework.databinding.FragmentNoteBinding
import com.example.a4homework.databinding.FragmentOnBoardBinding
import com.example.a4homework.ui.App

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),
    NoteAdapter.Result {

    private val adapter: NoteAdapter by lazy {
        NoteAdapter(this)
    }

    override fun setupUI() {
        val models = App.db.getNoteDao().getAllNote()

        adapter.setList(models)
        binding.rvNote.adapter = adapter
        binding.btnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", "addNote")
            findNavController().navigate(R.id.addNoteFragment)
        }
    }

    override fun longClick(model: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Вы уверены что хотите удалить")
        builder.setPositiveButton("да") { dialog, which ->
            App.db.getNoteDao().deleteNote(model)
            val list = App.db.getNoteDao().getAllNote()
            adapter.setList(list)
        }

        builder.setNegativeButton("нет") { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun clickChange(model: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Вы уверены что хотите изменить заметку")

        builder.setPositiveButton("Сортировать по дате") { dialog, which ->
            val list = App.db.getNoteDao().sortByDate()
            adapter.setList(list)
        }

        builder.setNegativeButton("Сортировать по алфавиту") { dialog, which ->
            val list = App.db.getNoteDao().sortByElement()
            adapter.setList(list)
        }
        builder.show()
    }

    override fun noteChange(model: NoteModel) {
        val bundle = Bundle()
        bundle.putString("id", "noteChange")
        bundle.putSerializable("noteModel", model)
        findNavController().navigate(R.id.addNoteFragment, bundle)
    }
}