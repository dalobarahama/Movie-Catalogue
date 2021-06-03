package com.example.moviecataloguejetpackpro.data.source.remote

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/upcoming")
    fun getUpcomingMovies(): Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTVOnTheAir(): Call<TVShowResponse>
}