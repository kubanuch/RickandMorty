package com.example.kotlin1lesson2.ui.fragments.characters


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.common.extensions.putDescription
import com.example.kotlin1lesson2.common.extensions.putName
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.databinding.FragmentCharactersBinding
import com.example.kotlin1lesson2.models.RickAndMortyCharacters
import com.example.kotlin1lesson2.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    R.layout.fragment_characters
) {
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this::onItemClickListener)

    override fun initialize() {
        binding.recyclerviewCharacter.adapter = characterAdapter
    }

    override fun setupObserves() {
        subscribeToCharacters()
    }

    private fun subscribeToCharacters() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.e("loo", "olo")
                }
                is Resource.Error -> {
                    Log.e("tag", "Error Character ${it.message.toString()}")
                }
                is Resource.Success -> {
                    it.data?.result?.let { it1 -> characterAdapter.setList(it1) }
                }
            }
        }
    }

    private fun onItemClickListener(model: RickAndMortyCharacters) {
        putName(model.name)
        putDescription(model.image)
        findNavController().navigate(R.id.detailFragment)
    }
}