package com.example.a4homework.ui.fragment.board

import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a4homework.R
import com.example.a4homework.databinding.FragmentOnBoardBinding
import com.example.a4homework.databinding.ItemBoardBinding

class BoardAdapter(private val listener: () -> Unit ) : Adapter<BoardAdapter.BoardViewHolder>() {
    private val imgList = listOf(R.drawable.img, R.drawable.img_1, R.drawable.img_2)
    private val titleList = listOf("Title 1", "Title 2", "Title 3")
    private val desList = listOf("Des 1", "Des 2", "Des 3")

    inner class BoardViewHolder(private val binding: ItemBoardBinding) : ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.itemImageBoard.setImageResource(imgList[position])
            binding.itemImageTitle.text = titleList[position]
            binding.itemImageDesc.text = desList[position]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BoardViewHolder (
        ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )



    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = imgList.size
}