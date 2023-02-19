package com.example.moviecataloguejetpackpro.common.composition.app

import android.app.Application
import com.example.moviecataloguejetpackpro.BuildConfig
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModule(val application: Application) {

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .build()
    }

    val apiService get() = retrofit.create(ApiService::class.java)

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

}