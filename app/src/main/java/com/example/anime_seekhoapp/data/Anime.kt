package com.example.anime_seekhoapp.data

data class Anime(
    val mal_id: Int,
    val title: String,
    val synopsis: String,
    val episodes: Int,
    val score: Double,
    val genres: List<Genre>,
    val trailer: Trailer?,
    val images: Images
)

data class Genre(
    val name: String
)

data class Trailer(
    val url: String?
)

data class Images(
    val jpg: JpgImage
)

data class JpgImage(
    val image_url: String
)