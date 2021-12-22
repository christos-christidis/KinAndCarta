package com.christidischristos.kinandcarta.ui.details

import android.webkit.URLUtil
import com.christidischristos.kinandcarta.R
import com.christidischristos.kinandcarta.databinding.ItemStoryElementBinding
import com.christidischristos.kinandcarta.extensions.gone
import com.christidischristos.kinandcarta.extensions.visible
import com.christidischristos.kinandcarta.ui.common.RvItem
import com.christidischristos.kinandcarta.ui.common.RvItemListAdapter
import com.christidischristos.kinandcarta.utils.loadImageFromUrl

class StoryElementRvItem(override val content: String) : RvItem {
    override val layout = R.layout.item_story_element
    override val id = content

    override fun bind(viewHolder: RvItemListAdapter.RvItemViewHolder) {
        ItemStoryElementBinding.bind(viewHolder.itemView).let { binding ->
            if (URLUtil.isValidUrl(content)) {
                binding.textTv.gone()
                binding.imageIv.visible()
                loadImageFromUrl(binding.root.context, content, binding.imageIv)
            } else {
                binding.textTv.visible(content)
                binding.imageIv.gone()
            }
        }
    }
}
