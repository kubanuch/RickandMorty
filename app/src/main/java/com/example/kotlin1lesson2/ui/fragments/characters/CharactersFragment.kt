package com.example.kotlin1lesson2.ui.fragments.characters


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.databinding.FragmentCharactersBinding
import com.example.kotlin1lesson2.ui.adapters.CharacterAdapter
import com.example.kotlin1lesson2.ui.adapters.LoaderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    R.layout.fragment_characters
) {
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by activityViewModels()
    private val characterAdapter = CharacterAdapter(this::onItemClickListener)

    override fun initialize() {
        binding.recyclerviewCharacter.adapter =
            characterAdapter.withLoadStateFooter(LoaderAdapter { characterAdapter.retry() })
    }
    override fun setupObserves() {
        subscribeToCharacters()
    }
    private fun subscribeToCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
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

