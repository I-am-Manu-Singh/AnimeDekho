package com.example.anime_seekhoapp.api

import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: Int): AnimeDetailResponse
}