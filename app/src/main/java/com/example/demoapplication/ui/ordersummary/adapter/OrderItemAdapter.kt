package com.example.demoapplication.ui.ordersummary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demoapplication.databinding.RawOrderItemLayoutBinding
import com.example.demoapplication.databinding.TopRawLayoutBinding
import com.example.demoapplication.utils.base.BaseRecyclerViewAdapter
import com.example.demoapplication.utils.loadImage
import com.example.demoapplication.utils.loadSqareImage

class OrderItemAdapter(
    val context: Context,
    val onItemClick: (Any?, Any?) -> Unit
) : BaseRecyclerViewAdapter<String, OrderItemAdapter.OrderItemViewHolder>() {


    override fun createItemViewHolder(parent: ViewGroup): OrderItemViewHolder {
        val itemBinding =
            RawOrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderItemViewHolder(itemBinding)
    }

    override fun bindItemViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
    inner class OrderItemViewHolder(private val itemBinding: RawOrderItemLayoutBinding) : ViewHolder(itemBinding.root){
        fun bind(data: String, position: Int) {
//            if (data != null){
                itemBinding.ivItemImage.loadSqareImage("https://picsum.photos/200/300?random=2")
//            }
//            itemBinding.tvTitle.text = "Transfer prescriptions"
            itemBinding.root.setOnClickListener {
                onItemClick.invoke(data, position)
            }
        }
    }
}