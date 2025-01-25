package com.example.anime_seekhoapp

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.anime_seekhoapp.api.ApiClient
import com.example.anime_seekhoapp.api.JikanApiService
import com.example.anime_seekhoapp.data.Anime
import com.example.anime_seekhoapp.databinding.ActivityDetailBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailActivity : ComponentActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val apiService = ApiClient.retrofit.create(JikanApiService::class.java)
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.videoView.player = exoPlayer // Assuming videoView is a PlayerView in the layout

        val animeId = intent.getIntExtra("anime_id", -1)
        if (animeId != -1) {
            fetchAnimeDetails(animeId)
        } else {
            Toast.makeText(this, "Invalid Anime ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchAnimeDetails(animeId: Int) {
        lifecycleScope.launch {
            try {
                val response = apiService.getAnimeDetails(animeId)
                displayAnimeDetails(response.data)
            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "Failed to load details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayAnimeDetails(anime: Anime) {

        // Picasso for loading poster image
        Picasso.get()
            .load(anime.images.jpg.image_url) // Load the image from the URL
            .into(binding.poster) // Set the ImageView where the image will be loaded


        // Setting title with custom style
        binding.title.text = anime.title
        binding.title.setTextColor(ContextCompat.getColor(this, R.color.title_color))
        binding.title.setTypeface(null, Typeface.BOLD)
        binding.title.textSize = 18f

        // Setting synopsis with custom style
        binding.synopsis.text = anime.synopsis
        binding.synopsis.setTextColor(ContextCompat.getColor(this, R.color.synopsis_color))
        binding.synopsis.setTypeface(null, Typeface.NORMAL)
        binding.synopsis.textSize = 14f

        // Setting episodes with custom style
        binding.episodes.text = "Episodes: ${anime.episodes}"
        binding.episodes.setTextColor(ContextCompat.getColor(this, R.color.episodes_color))
        binding.episodes.setTypeface(null, Typeface.NORMAL)
        binding.episodes.textSize = 16f

        // Setting rating with custom style
        binding.rating.text = "Rating: ${anime.score}"
        binding.rating.setTextColor(ContextCompat.getColor(this, R.color.rating_color))
        binding.rating.setTypeface(null, Typeface.BOLD)
        binding.rating.textSize = 14f

        // Setting genres with custom style
        binding.genres.text = "Genres: ${anime.genres.joinToString { it.name }}"
        binding.genres.setTextColor(ContextCompat.getColor(this, R.color.genres_color))
        binding.genres.setTypeface(null, Typeface.NORMAL)
        binding.genres.textSize = 14f

        // In your activity's onCreate or a similar initialization method
        val posterImageView: ImageView = findViewById(R.id.poster) // ImageView for the poster
        val videoView: PlayerView = findViewById(R.id.videoView) // ExoPlayer's PlayerView
        val videoUrl = anime.trailer?.url
        anime.images.jpg.image_url // URL for the poster image

        if (!videoUrl.isNullOrEmpty()) {
            // Show the PlayerView and hide the poster image
            videoView.visibility = View.VISIBLE

            // Show video URL in a Toast
            Toast.makeText(this@DetailActivity, "Video URL: $videoUrl", Toast.LENGTH_LONG).show()

            val mediaItem = MediaItem.fromUri(videoUrl)

            // Check if the URL contains "m3u8" to decide if it's an HLS stream
            val mediaSource = if (videoUrl.contains("m3u8", ignoreCase = true)) {
                // HLS stream
                HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
                    .createMediaSource(mediaItem)
            } else {
                // Progressive (MP4, etc.)
                ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                    .createMediaSource(mediaItem)
            }

            exoPlayer.setMediaSource(mediaSource)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        } else {
            // Hide the PlayerView and show the poster image
            videoView.visibility = View.GONE
            Picasso.get()
                .load(anime.images.jpg.image_url) // Load the image from the URL
                .into(binding.poster) // Set the ImageView where the image will be loaded
            // Show a Toast message for no trailer URL
            Toast.makeText(this@DetailActivity, "No trailer URL available", Toast.LENGTH_SHORT)
                .show()
        }

        // Handle activity lifecycle
        fun onPause() {
            super.onPause()
            exoPlayer.pause() // Pause video when activity goes to background
        }

        fun onDestroy() {
            super.onDestroy()
            exoPlayer.release() // Release the player to free resources
        }
    }
}