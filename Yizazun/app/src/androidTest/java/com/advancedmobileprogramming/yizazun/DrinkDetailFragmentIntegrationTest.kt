package com.example.Yizazun

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class DrinkDetailFragmentIntegrationTest {
    private lateinit var scenario: FragmentScenario<DrinkDetailFragment>

    @Before
    fun initFragment() {
        scenario = launchFragmentInContainer<DrinkDetailFragment>()
    }

    @Test
    fun testStaticLabels(){
        onView(withId(R.id.textView3)).check(matches(withText("Drink name")))
        onView(withId(R.id.textView5)).check(matches(withText("Description")))
        
    }

    @After
    fun destroyFragment(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}