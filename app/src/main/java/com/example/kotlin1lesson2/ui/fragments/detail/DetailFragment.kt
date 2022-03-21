package com.example.kotlin1lesson2.ui.fragments.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.common.extensions.image
import com.example.kotlin1lesson2.common.extensions.name
import com.example.kotlin1lesson2.common.extensions.setImage
import com.example.kotlin1lesson2.databinding.FragmentDetailBinding
import com.example.kotlin1lesson2.ui.fragments.characters.CharactersViewModel
import kotlinx.coroutines.launch

class DetailFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>(
    R.layout.fragment_detail) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()

    override fun setupViews() {
        viewLifecycleOwner.lifecycleScope.launch {
            image.observe(viewLifecycleOwner) { image ->
                binding.image.setImage(image)
                binding.tvtName.text = name.value
            }
        }
    }
}
