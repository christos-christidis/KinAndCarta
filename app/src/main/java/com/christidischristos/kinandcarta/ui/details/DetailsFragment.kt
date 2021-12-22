package com.christidischristos.kinandcarta.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.christidischristos.kinandcarta.databinding.FragmentDetailsBinding
import com.christidischristos.kinandcarta.ui.base.BaseFragment
import com.christidischristos.kinandcarta.ui.common.RvItemListAdapter
import com.christidischristos.kinandcarta.utils.loadImageFromUrl

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val args by navArgs<DetailsFragmentArgs>()
    private val caseStudy by lazy { args.caseStudy }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clientTv.text = caseStudy.client
        binding.titleTv.text = caseStudy.title
        loadImageFromUrl(requireContext(), caseStudy.heroImageUrl, binding.heroImageIv)
        setUpStoryRv()
    }

    private fun setUpStoryRv() {
        val storyAdapter = RvItemListAdapter()
        binding.storyRv.adapter = storyAdapter
        storyAdapter.submitList(caseStudy.storySections.mapIndexed { idx, section ->
            StorySectionRvItem(
                StorySectionRvItem.Content(
                    storySection = section,
                    category = if (idx == 0) caseStudy.category else ""
                )
            )
        })
    }
}
