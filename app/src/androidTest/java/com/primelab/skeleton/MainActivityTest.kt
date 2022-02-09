package com.primelab.skeleton

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
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
@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Mock
    private lateinit var resources: Resources
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        `when`(resources.getString(R.string.hello_word))
            .thenReturn(context.getString(R.string.hello_word))
    }

    @Test
    fun launchTest() {
        activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun viewTest() {
        onView(withId(R.id.hello_word))
            .perform(click())
            .check(matches(isDisplayed()))
            .check(matches(withText(resources.getString(R.string.hello_word))))
    }

    @After
    fun release() {
        activityRule.scenario.close()
    }
}