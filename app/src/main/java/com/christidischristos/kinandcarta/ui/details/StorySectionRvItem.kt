package com.christidischristos.kinandcarta.ui.details

import androidx.core.content.ContextCompat
import com.christidischristos.kinandcarta.R
import com.christidischristos.kinandcarta.databinding.ItemStorySectionBinding
import com.christidischristos.kinandcarta.domain.StorySection
import com.christidischristos.kinandcarta.extensions.gone
import com.christidischristos.kinandcarta.extensions.visible
import com.christidischristos.kinandcarta.extensions.visibleOrGone
import com.christidischristos.kinandcarta.ui.common.RvItem
import com.christidischristos.kinandcarta.ui.common.RvItemListAdapter

class StorySectionRvItem(override val content: Content) : RvItem {
    override val layout = R.layout.item_story_section
    override val id = content

    override fun bind(viewHolder: RvItemListAdapter.RvItemViewHolder) {
        ItemStorySectionBinding.bind(viewHolder.itemView).let { binding ->
            val adapter = RvItemListAdapter()
            binding.storyElementsRv.adapter = adapter
            val hasTitle = content.storySection.title.isNullOrBlank().not()
            if (hasTitle) {
                binding.titleTv.text = content.storySection.title
                binding.divider.gone()
                adapter.submitList(content.storySection.bodyElements.map {
                    StoryElementRvItem(it)
                })
            } else {
                val firstElement = content.storySection.bodyElements.first()
                val restOfElements = content.storySection.bodyElements.minus(firstElement)
                binding.titleTv.text = firstElement
                binding.divider.visible()
                adapter.submitList(restOfElements.map {
                    StoryElementRvItem(it)
                })
            }
            binding.categoryTv.apply {
                visibleOrGone(content.category.isNotBlank())
                text = context.getString(R.string.category_x, content.category)
            }
            val bgColor = ContextCompat.getColor(
                binding.root.context,
                if (hasTitle) R.color.less_dark_bg else R.color.dark_bg
            )
            binding.root.setBackgroundColor(bgColor)
        }
    }

    data class Content(
        val storySection: StorySection,
        val category: String
    )
}
