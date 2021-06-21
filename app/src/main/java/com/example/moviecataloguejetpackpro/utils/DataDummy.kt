package com.example.moviecataloguejetpackpro.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        val genreIds: List<Int> = mutableListOf(1, 2, 3)

        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                false,
                "Aquaman",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "12/21/2018 (US)",
                1897.772,
                7.5,
                460465,
                false,
                3036
            )
        )
        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                false,
                "Aquaman",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "12/21/2018 (US)",
                1897.772,
                7.5,
                460465,
                false,
                3036
            )
        )
        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                false,
                "Aquaman",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "12/21/2018 (US)",
                1897.772,
                7.5,
                460465,
                false,
                3036
            )
        )

        return movies
    }

    fun generateDummyTVShows(): List<TVShowEntity> {
        val tvShows = ArrayList<TVShowEntity>()

        val genreIds: List<Int> = mutableListOf(1, 2, 3)
        val originalCountry: List<String> = mutableListOf("US")

        tvShows.add(
            TVShowEntity(
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                originalCountry,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Loki",
                6982.349,
                8.1,
                "Loki",
                84958,
                3271
            )
        )
        tvShows.add(
            TVShowEntity(
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                originalCountry,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Loki",
                6982.349,
                8.1,
                "Loki",
                84958,
                3271
            )
        )
        tvShows.add(
            TVShowEntity(
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                originalCountry,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Loki",
                6982.349,
                8.1,
                "Loki",
                84958,
                3271
            )
        )

        return tvShows
    }

    fun generateDummyLiveDataMovies(): LiveData<List<MovieEntity>> {
        val movies = ArrayList<MovieEntity>()
        val _movieList = MutableLiveData<List<MovieEntity>>()
        val movieList: LiveData<List<MovieEntity>> = _movieList

        val genreIds: List<Int> = mutableListOf(1, 2, 3)

        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                false,
                "Aquaman",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "12/21/2018 (US)",
                1897.772,
                7.5,
                460465,
                false,
                3036
            )
        )
        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                false,
                "Aquaman",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "12/21/2018 (US)",
                1897.772,
                7.5,
                460465,
                false,
                3036
            )
        )
        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                false,
                "Aquaman",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "12/21/2018 (US)",
                1897.772,
                7.5,
                460465,
                false,
                3036
            )
        )
        _movieList.value = movies

        return movieList
    }

    fun generateDummyLiveDataTVShows(): LiveData<List<TVShowEntity>> {
        val tvShows = ArrayList<TVShowEntity>()

        val _tvShowList = MutableLiveData<List<TVShowEntity>>()
        val tvShowList: LiveData<List<TVShowEntity>> = _tvShowList

        val genreIds: List<Int> = mutableListOf(1, 2, 3)
        val originalCountry: List<String> = mutableListOf("US")

        tvShows.add(
            TVShowEntity(
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                originalCountry,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Loki",
                6982.349,
                8.1,
                "Loki",
                84958,
                3271
            )
        )
        tvShows.add(
            TVShowEntity(
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                originalCountry,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Loki",
                6982.349,
                8.1,
                "Loki",
                84958,
                3271
            )
        )
        tvShows.add(
            TVShowEntity(
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                genreIds,
                "https://image.tmdb.org/t/p/w500/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                originalCountry,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Loki",
                6982.349,
                8.1,
                "Loki",
                84958,
                3271
            )
        )
        _tvShowList.value = tvShows

        return tvShowList
    }
}
