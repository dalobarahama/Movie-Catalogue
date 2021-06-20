package com.example.moviecataloguejetpackpro.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecataloguejetpackpro.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTVShow = DataDummy.generateDummyTVShows()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

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
                1,
                click()
            )
        )
        onView(withId(R.id.title_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail_activity)).check(matches(withText(dummyMovie[1].title)))
        onView(withId(R.id.overview_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail_activity)).check(matches(withText(dummyMovie[1].overview)))
        onView(withId(R.id.release_date_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_detail_activity)).check(matches(withText(dummyMovie[1].releaseDate)))
        onView(withId(R.id.score_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.score_detail_activity)).check(matches(withText(dummyMovie[1].score)))
        onView(withId(R.id.tags_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.tags_detail_activity)).check(matches(withText(dummyMovie[1].tags)))
        onView(withId(R.id.poster_detail_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.tvshows_recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.title_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail_activity)).check(matches(withText(dummyTVShow[1].title)))
        onView(withId(R.id.overview_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail_activity)).check(matches(withText(dummyTVShow[1].overview)))
        onView(withId(R.id.release_date_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_detail_activity)).check(matches(withText(dummyTVShow[1].releaseDate)))
        onView(withId(R.id.score_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.score_detail_activity)).check(matches(withText(dummyTVShow[1].score)))
        onView(withId(R.id.tags_detail_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.tags_detail_activity)).check(matches(withText(dummyTVShow[1].tags)))
        onView(withId(R.id.poster_detail_activity)).check(matches(isDisplayed()))
    }
}