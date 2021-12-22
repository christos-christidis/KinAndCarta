package com.christidischristos.kinandcarta.ui.common

import androidx.annotation.LayoutRes

interface RvItem {

    @get:LayoutRes
    val layout: Int

    val id: Any

    val content: Any?

    fun bind(viewHolder: RvItemListAdapter.RvItemViewHolder)
}
