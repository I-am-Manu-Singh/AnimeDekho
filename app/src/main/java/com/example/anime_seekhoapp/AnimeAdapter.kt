package com.example.anime_seekhoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anime_seekhoapp.data.Anime
import com.example.anime_seekhoapp.databinding.AnimeItemBinding
import com.squareup.picasso.Picasso

class AnimeAdapter(
    private val animeList: List<Anime>,
    private val onItemClick: (Anime) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.bind(anime)
    }

    override fun getItemCount(): Int = animeList.size

    inner class AnimeViewHolder(private val binding: AnimeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.title.text = anime.title
            binding.episodes.text = "Episodes: ${anime.episodes}"
            binding.rating.text = "Rating: ${anime.score}"

            Picasso.get().load(anime.images.jpg.image_url).into(binding.poster)

            binding.root.setOnClickListener {
                onItemClick(anime)
            }
        }
    }
}
