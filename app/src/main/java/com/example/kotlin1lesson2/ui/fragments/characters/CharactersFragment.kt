package com.example.kotlin1lesson2.ui.fragments.characters

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.common.extensions.submitData
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.databinding.FragmentCharactersBinding
import com.example.kotlin1lesson2.ui.adapters.CharacterAdapter
import com.example.kotlin1lesson2.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    R.layout.fragment_characters
) {
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this::onItemClickListener)

    override fun setupViews() {
        setupAdapter()
    }

    override fun setupObserves() {
        subscribeToCharacters()
        subscribeToCharactersLocale()

    }

    private fun subscribeToCharactersLocale() {
        lifecycleScope.launch {
            viewModel.getCharacters().collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.e("че смотришь", " ")
                    }
                    is Resource.Error -> {
                        Log.e(" ololo", it.message.toString())
                    }
                    is Resource.Success -> {
                        it.data?.let {
                            characterAdapter.submitData(it)
                        }
                    }
                }
            }
        }
    }

    private fun setupAdapter() = with(binding.recyclerviewCharacter) {
        adapter = characterAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, { viewModel.fetchCharacter() }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterState.observe(viewLifecycleOwner) {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun onItemClickListener(id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToDetailFragment(id)
        )
    }
}
