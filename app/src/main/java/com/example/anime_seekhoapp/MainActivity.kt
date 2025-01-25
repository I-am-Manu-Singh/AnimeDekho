package com.example.anime_seekhoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anime_seekhoapp.api.ApiClient
import com.example.anime_seekhoapp.api.JikanApiService
import com.example.anime_seekhoapp.data.Anime
import com.example.anime_seekhoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiService = ApiClient.retrofit.create(JikanApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        lifecycleScope.launch {
            try {
                val response = apiService.getTopAnime()
                setupRecyclerView(response.data)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView(animeList: List<Anime>) {
        val adapter = AnimeAdapter(animeList) { anime ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("anime_id", anime.mal_id)
            startActivity(intent)
        }

        binding.animeRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.animeRecyclerView.adapter = adapter
    }
}
