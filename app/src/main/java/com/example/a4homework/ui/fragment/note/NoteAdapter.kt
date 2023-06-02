package com.example.a4homework.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a4homework.data.model.NoteModel
import com.example.a4homework.databinding.ItemListBinding

class NoteAdapter(
    private val click: Result
): Adapter<NoteAdapter.NoteViewHolder>() {

    private val list = ArrayList<NoteModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(val binding: ItemListBinding): ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            binding.tvDate.text = model.date
            binding.tvTitle.text = model.title
            binding.tvDesc.text = model.desc
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnLongClickListener {
            click.clickChange(list[position])
            true
        }

        holder.binding.imgDelete.setOnClickListener {
            click.longClick(list[position])
        }

        holder.itemView.setOnClickListener {
            click.noteChange(list[position])
        }
    }

    override fun getItemCount(): Int = list.size


    interface Result{
        fun longClick(model: NoteModel)
        fun clickChange(model: NoteModel)
        fun noteChange(model: NoteModel)
    }
}