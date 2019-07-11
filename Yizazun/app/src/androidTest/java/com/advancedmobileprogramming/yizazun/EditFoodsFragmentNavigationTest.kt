package com.example.Yizazun

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class EditFoodFragmentNavigationTest {
    @Test
    fun click_saveButton_navigateTo_homeFragment() {
        val scenario = launchFragmentInContainer<EditFoodFragment>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }


        Espresso.onView(ViewMatchers.withId(R.id.save)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            EditFoodFragmentDirections.action_editFoodFragment_to_homeFragment())
    }


}
