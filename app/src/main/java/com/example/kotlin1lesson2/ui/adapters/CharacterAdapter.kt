package com.example.kotlin1lesson2.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson2.common.extensions.setImage
import com.example.kotlin1lesson2.databinding.ItemCharactersBinding
import com.example.kotlin1lesson2.models.RickAndMortyCharacters

class CharacterAdapter(val onItemClickListener: (model: RickAndMortyCharacters) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var list: List<RickAndMortyCharacters> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacterViewHolder =
        CharacterViewHolder(ItemCharactersBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.onBind(list[position])


    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<RickAndMortyCharacters>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(data: RickAndMortyCharacters) {
            binding.imageCharacter.setImage(data.image)
            binding.tvName.text = data.name
            binding.root.setOnClickListener {
                onItemClickListener(data)
            }

        }


    }
}