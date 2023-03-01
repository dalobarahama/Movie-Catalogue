package com.example.moviecataloguejetpackpro.data.source.remote

import com.example.moviecataloguejetpackpro.data.source.remote.response.MovieResponse
import com.example.moviecataloguejetpackpro.data.source.remote.response.TVShowResponse
import com.example.moviecataloguejetpackpro.data.source.remote.response.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("tv/on_the_air")
    suspend fun getTVOnTheAir(): Response<TVShowResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
    ): Response<TrendingResponse>

}