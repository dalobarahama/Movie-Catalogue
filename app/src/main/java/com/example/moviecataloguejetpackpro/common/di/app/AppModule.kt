package com.example.moviecataloguejetpackpro.common.di.app

import android.app.Application
import com.example.moviecataloguejetpackpro.BuildConfig
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {

    @Provides
    fun client(): OkHttpClient {
        return OkHttpClient.Builder()
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

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun retrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .build()
    }

    @Provides
    @AppScope
    fun apiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

}