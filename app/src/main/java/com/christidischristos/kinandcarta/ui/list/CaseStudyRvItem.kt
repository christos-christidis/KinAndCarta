package com.christidischristos.kinandcarta.ui.list

import com.christidischristos.kinandcarta.R
import com.christidischristos.kinandcarta.databinding.ItemCaseStudyBinding
import com.christidischristos.kinandcarta.ui.common.RvItem
import com.christidischristos.kinandcarta.ui.common.RvItemListAdapter
import com.christidischristos.kinandcarta.utils.loadImageFromUrl

class CaseStudyRvItem(
    override val content: Content,
    private val onClick: () -> Unit
) : RvItem {

    override val layout = R.layout.item_case_study
    override val id = content

    override fun bind(viewHolder: RvItemListAdapter.RvItemViewHolder) {
        ItemCaseStudyBinding.bind(viewHolder.itemView).let { binding ->
            loadImageFromUrl(binding.root.context, content.heroImageUrl, binding.heroImageIv)
            binding.categoryTv.text = content.category
            binding.titleTv.text = content.title
            binding.clientTv.text = content.client
            binding.root.setOnClickListener { onClick() }
        }
    }

    data class Content(
        val heroImageUrl: String,
        val category: String,
        val title: String,
        val client: String?
    )
}
