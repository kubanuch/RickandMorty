package com.example.kotlin1lesson2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson2.base.BaseDiffUtil
import com.example.kotlin1lesson2.common.extensions.setImage
import com.example.kotlin1lesson2.databinding.ItemCharactersBinding
import com.example.kotlin1lesson2.models.RickAndMortyCharacters

class CharacterAdapter(val onItemClickListener: (id: Int) -> Unit) :
    PagingDataAdapter<RickAndMortyCharacters, CharacterAdapter.CharacterViewHolder>(
        BaseDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): CharacterViewHolder =
        CharacterViewHolder(ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(private val binding: ItemCharactersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RickAndMortyCharacters) {
            binding.imageCharacter.setImage(data.image)
            binding.tvName.text = data.name
        }


        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { m -> onItemClickListener(m.id) }
            }
        }
    }
}
