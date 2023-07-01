package com.example.demoapplication.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demoapplication.databinding.TopRawLayoutBinding
import com.example.demoapplication.utils.base.BaseRecyclerViewAdapter
import com.example.demoapplication.utils.loadImage
import com.example.demoapplication.utils.loadSqareImage

class TopListAdapter(
    val context: Context,
    val onItemClick: (Any?, Any?) -> Unit
) : BaseRecyclerViewAdapter<String, TopListAdapter.TopListViewHolder>() {


    override fun createItemViewHolder(parent: ViewGroup): TopListViewHolder {
        val itemBinding =
            TopRawLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopListViewHolder(itemBinding)
    }

    override fun bindItemViewHolder(holder: TopListViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
    inner class TopListViewHolder(private val itemBinding: TopRawLayoutBinding) : ViewHolder(itemBinding.root){
        fun bind(data: String, position: Int) {
//            if (data != null){
                itemBinding.ivItemImage.loadSqareImage("https://picsum.photos/200/300?random=2")
//            }
            itemBinding.tvItemName.text = "Transfer prescriptions"
            itemBinding.root.setOnClickListener {
                onItemClick.invoke(data, position)
            }
        }
    }
}