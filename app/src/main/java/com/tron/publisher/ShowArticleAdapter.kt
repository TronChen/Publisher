package com.tron.publisher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tron.publisher.databinding.ItemListBinding

class ShowArticleAdapter : ListAdapter<PDResult, ShowArticleAdapter.ItemViewHolder>(DiffCallback){

    class ItemViewHolder(var binding: ItemListBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pdResult: PDResult) {
            pdResult.let {
                binding.pdResult = it
                binding.executePendingBindings()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PDResult>() {
        override fun areItemsTheSame(oldItem: PDResult, newItem: PDResult): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: PDResult, newItem: PDResult): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val publisherData = getItem(position)
        holder.bind(publisherData)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}