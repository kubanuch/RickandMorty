package com.example.kotlin1lesson2.ui.fragments.episodes

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.common.extensions.submitData
import com.example.kotlin1lesson2.databinding.FragmentEpisodesBinding
import com.example.kotlin1lesson2.ui.adapters.EpisodesAdapter
import com.example.kotlin1lesson2.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    R.layout.fragment_episodes
) {

    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val episodesAdapter = EpisodesAdapter()


    override fun setupViews() {
        setupAdapter()
    }

    override fun setupObserves() {
        subscribeToEpisodes()
    }

    private fun setupAdapter() = with(binding.recyclerviewEpisodes) {
        adapter = episodesAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, { viewModel.fetchEpisodes() }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToEpisodes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodesState.observe(viewLifecycleOwner) {
                episodesAdapter.submitData(it)
            }
        }
    }
}



