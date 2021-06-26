package com.example.moviecataloguejetpackpro.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecataloguejetpackpro.data.source.local.LocalDataSource
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.data.source.remote.ApiResponse
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.AppExecutors
import com.example.moviecataloguejetpackpro.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource,
                    localDataSource,
                    appExecutors).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntityLocal>>> {
        Log.i("Repository", "getAllMovies")
        return object :
            NetworkBoundResource<PagedList<MovieEntityLocal>, List<MovieEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntityLocal>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntityLocal>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> =
                remoteDataSource.getMovies()


            override fun saveCallResult(data: List<MovieEntity>) {
                val movieList = ArrayList<MovieEntityLocal>()
                for (response in data) {
                    val movie = MovieEntityLocal(
                        response.id,
                        response.overview,
                        response.title,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntityLocal>>> {
        Log.i("Repository", "getAllTvShow")
        return object :
            NetworkBoundResource<PagedList<TvShowEntityLocal>, List<TVShowEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntityLocal>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }


            override fun shouldFetch(data: PagedList<TvShowEntityLocal>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<TVShowEntity>>> =
                remoteDataSource.getTvShows()


            override fun saveCallResult(data: List<TVShowEntity>) {
                val tvShowList = ArrayList<TvShowEntityLocal>()
                for (response in data) {
                    val tvShow = TvShowEntityLocal(
                        response.id,
                        response.firstAirDate,
                        response.overview,
                        response.posterPath,
                        response.voteAverage,
                        response.name,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovie(movieId: Int): LiveData<Resource<MovieEntityLocal>> {
        return object : NetworkBoundResource<MovieEntityLocal, List<MovieEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntityLocal> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntityLocal?): Boolean =
                data?.id == null

            override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieEntity>) {
                val movieList = ArrayList<MovieEntityLocal>()
                for (response in data) {
                    val movie = MovieEntityLocal(
                        response.id,
                        response.overview,
                        response.title,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntityLocal>> {
        return object : NetworkBoundResource<TvShowEntityLocal, List<TVShowEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntityLocal> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntityLocal?): Boolean =
                data?.id == null

            override fun createCall(): LiveData<ApiResponse<List<TVShowEntity>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TVShowEntity>) {
                val tvShowList = ArrayList<TvShowEntityLocal>()
                for (response in data) {
                    val tvShow = TvShowEntityLocal(
                        response.id,
                        response.firstAirDate,
                        response.overview,
                        response.posterPath,
                        response.voteAverage,
                        response.name,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getBookmarkedMovies(): LiveData<PagedList<MovieEntityLocal>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedMovies(), config).build()
    }

    override fun getBookmarkedTvShows(): LiveData<PagedList<TvShowEntityLocal>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedTvShows(), config).build()
    }


    override fun setMovieBookmark(movie: MovieEntityLocal, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movie, state) }


    override fun setTvShowBookmark(tvShow: TvShowEntityLocal, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowBookmark(tvShow, state) }

}