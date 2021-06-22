package com.example.moviecataloguejetpackpro.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = DataDummy.generateDummyDataMovies()
    private val dummyTVShow = DataDummy.generateDummyDataTVShows()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        delayTwoSeconds()
        onView(withId(R.id.movies_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_recyclerview)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadTVShows() {
        delayTwoSeconds()
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
        delayTwoSeconds()
        onView(withId(R.id.movies_recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        delayTwoSeconds()
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
        delayTwoSeconds()
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.tvshows_recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        delayTwoSeconds()
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

    private fun delayTwoSeconds() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}