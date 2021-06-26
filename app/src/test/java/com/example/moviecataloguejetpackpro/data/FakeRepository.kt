package com.example.moviecataloguejetpackpro.data

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.LocalDataSource
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.data.source.remote.ApiResponse
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.AppExecutors
import com.example.moviecataloguejetpackpro.vo.Resource

class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : DataSource {

    override fun getAllMovies(): LiveData<Resource<List<MovieEntityLocal>>> {
        return object :
            NetworkBoundResource<List<MovieEntityLocal>, List<MovieEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntityLocal>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntityLocal>?): Boolean =
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

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntityLocal>>> {
        return object :
            NetworkBoundResource<List<TvShowEntityLocal>, List<TVShowEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntityLocal>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntityLocal>?): Boolean =
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

    override fun getBookmarkedMovies(): LiveData<List<MovieEntityLocal>> =
        localDataSource.getBookmarkedMovies()

    override fun getBookmarkedTvShows(): LiveData<List<TvShowEntityLocal>> =
        localDataSource.getBookmarkedTvShows()

    override fun setMovieBookmark(movie: MovieEntityLocal, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movie, state) }

    override fun setTvShowBookmark(tvShow: TvShowEntityLocal, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowBookmark(tvShow, state) }
}