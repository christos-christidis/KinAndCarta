package com.christidischristos.kinandcarta.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RvItemListAdapter : ListAdapter<RvItem, RecyclerView.ViewHolder>(rvItemDiffCallback) {

    @LayoutRes
    override fun getItemViewType(position: Int): Int {
        return getItem(position).layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, layout: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(layout, parent, false)
        return RvItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).bind(holder as RvItemViewHolder)
    }

    class RvItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

private val rvItemDiffCallback: DiffUtil.ItemCallback<RvItem> =
    object : DiffUtil.ItemCallback<RvItem>() {
        override fun areItemsTheSame(oldItem: RvItem, newItem: RvItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RvItem, newItem: RvItem): Boolean {
            return oldItem.content == newItem.content
        }
    }
