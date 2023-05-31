package com.example.a4homework.ui.fragment.contact

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a4homework.databinding.ListContactBinding

class ContactAdapter(
    private val click: Result
): Adapter<ContactAdapter.ContactViewHolder>() {


    private val list = ArrayList<ContactModel>()


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ContactModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(val binding: ListContactBinding): ViewHolder(binding.root) {
        fun onBind(model: ContactModel) {
            binding.tvName.text = model.name
            binding.tvPhone.text = model.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(ListContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.binding.imgCall.setOnClickListener {
            click.call(list[position])
        }

        holder.binding.imgMsg.setOnClickListener {
            click.chat(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    interface Result {
        fun call(model: ContactModel)
        fun chat(model: ContactModel)
    }

}