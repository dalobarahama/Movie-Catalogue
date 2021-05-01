package com.example.moviecataloguejetpackpro

object DataDummy {
    fun generateDummyMovies(): List<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                "movie1",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure, Fantasy",
                "69%",
                "12/21/2018 (US)"
            )
        )
        movies.add(
            Movie(
                "movie2",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure",
                "72%",
                "02/14/2019 (US)"
            )
        )

        return movies
    }
}