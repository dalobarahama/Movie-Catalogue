package com.example.moviecataloguejetpackpro.data.source.remote

import com.example.moviecataloguejetpackpro.data.source.remote.response.MovieResponse
import com.example.moviecataloguejetpackpro.data.source.remote.response.TVShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("tv/on_the_air")
    suspend fun getTVOnTheAir(): Response<TVShowResponse>

}