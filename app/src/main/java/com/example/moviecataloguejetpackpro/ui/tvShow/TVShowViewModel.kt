package com.example.moviecataloguejetpackpro.ui.tvShow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiConfig
import com.example.moviecataloguejetpackpro.data.source.remote.TVShow
import com.example.moviecataloguejetpackpro.data.source.remote.TVShowResponse
import com.example.moviecataloguejetpackpro.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowViewModel : ViewModel() {

    companion object {
        private const val TAG = "TVShowViewModel"
        private const val API_KEY = R.string.THE_MOVIEDB_API_KEY.toString()
    }

    private val _tvShowList = MutableLiveData<List<TVShow>>()
    val tvShowList: LiveData<List<TVShow>> = _tvShowList

    init {
        getTVShowApi()
    }

    private fun getTVShowApi() {
        val client = ApiConfig.getApiService(API_KEY).getTVOnTheAir()
        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>,
            ) {
                if (response.isSuccessful) {
                    _tvShowList.value = response.body()?.tvShows
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    var tvShows: MutableLiveData<List<MovieEntity>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData<List<MovieEntity>>()
                getTVShow()
            }
            return field
        }
        private set

    fun getTVShow(): List<MovieEntity> = DataDummy.generateDummyTVShows()

}