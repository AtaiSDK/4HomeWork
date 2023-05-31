package com.example.a4homework.ui.fragment.firestore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4homework.databinding.ItemListBinding

class FireAdapter: RecyclerView.Adapter<FireAdapter.FireViewHolder>() {

    private val list = ArrayList<FireModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FireModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    inner class FireViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: FireModel) {
            binding.tvDate.text = model.date
            binding.tvTitle.text = model.title
            binding.tvDesc.text = model.desc
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FireViewHolder =
        FireViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )


    override fun onBindViewHolder(holder: FireViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}