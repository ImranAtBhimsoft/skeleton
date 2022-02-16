package com.primelab.skeleton

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 09/02/2022.
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<MainActivity>()

    private lateinit var context: Context

    @Before
    fun setup() {
        hiltRule.inject()
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun launchTest() {
        activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun viewTest() {
        onView(withId(R.id.app_bar_layout))
            .perform(click())
            .check(matches(isDisplayed()))
        onView(withId(R.id.bottom_navigation_view))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @After
    fun release() {
        activityRule.scenario.close()
    }
}