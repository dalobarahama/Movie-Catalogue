package com.example.moviecataloguejetpackpro.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.example.moviecataloguejetpackpro.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = DataDummy.generateDummyDataMovies()
    private val dummyTVShow = DataDummy.generateDummyDataTVShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.movies_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_recyclerview)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadTVShows() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.tvshows_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_recyclerview)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTVShow.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.movies_recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.title_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail_activity)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.overview_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail_activity)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.release_date_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_detail_activity)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.score_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.score_detail_activity)).check(matches(withText(dummyMovie[0].voteAverage.toString())))
        onView(withId(R.id.poster_detail_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.tvshows_recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.title_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail_activity)).check(matches(withText(dummyTVShow[0].name)))
        onView(withId(R.id.overview_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail_activity)).check(matches(withText(dummyTVShow[0].overview)))
        onView(withId(R.id.release_date_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_detail_activity)).check(matches(withText(dummyTVShow[0].firstAirDate)))
        onView(withId(R.id.score_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.score_detail_activity)).check(matches(withText(dummyTVShow[0].voteAverage.toString())))
        onView(withId(R.id.poster_detail_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadMovieBookmarks() {
        onView(withId(R.id.movies_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.movies_bookmark)).perform(click())
        onView(withId(R.id.movies_bookmark_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_bookmark_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click())
        )
        onView(withId(R.id.title_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail_activity)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.overview_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail_activity)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.release_date_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_detail_activity)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.score_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.score_detail_activity)).check(matches(withText(dummyMovie[0].voteAverage.toString())))
        onView(withId(R.id.poster_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTvShowBookmarks() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.tvshows_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.tv_shows_bookmark)).perform(click())
        onView(withId(R.id.tv_shows_bookmark_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_shows_bookmark_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click())
        )
        onView(withId(R.id.title_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail_activity)).check(matches(withText(dummyTVShow[0].name)))
        onView(withId(R.id.overview_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail_activity)).check(matches(withText(dummyTVShow[0].overview)))
        onView(withId(R.id.release_date_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_detail_activity)).check(matches(withText(dummyTVShow[0].firstAirDate)))
        onView(withId(R.id.score_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.score_detail_activity)).check(matches(withText(dummyTVShow[0].voteAverage.toString())))
        onView(withId(R.id.poster_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}