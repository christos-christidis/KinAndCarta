package com.christidischristos.kinandcarta.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.christidischristos.kinandcarta.databinding.FragmentListBinding
import com.christidischristos.kinandcarta.ui.base.BaseFragment
import com.christidischristos.kinandcarta.ui.common.RvItemListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    private val viewModel by viewModels<ListViewModel>()

    private val casesAdapter = RvItemListAdapter()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.caseStudiesRv.adapter = casesAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.caseStudies.observe(viewLifecycleOwner, { caseStudies ->
            casesAdapter.submitList(caseStudies.map {
                CaseStudyRvItem(
                    CaseStudyRvItem.Content(
                        heroImageUrl = it.heroImageUrl,
                        category = it.category,
                        title = it.teaser ?: it.title,
                        client = it.client
                    ),
                    onClick = {
                        findNavController().navigate(ListFragmentDirections.actionDetails(it))
                    }
                )
            })
        })
    }
}
