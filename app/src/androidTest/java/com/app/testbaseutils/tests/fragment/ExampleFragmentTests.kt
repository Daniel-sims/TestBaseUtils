package com.app.testbaseutils.tests.fragment

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.testbaseutils.ExampleFragment
import com.app.testbaseutils.R
import com.app.testbaseutils.robots.ExampleFragmentRobot
import com.app.testbaseutils.testutils.base.TestBase
import kotlinx.android.synthetic.main.fragment_example.*
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers.`is`

@RunWith(AndroidJUnit4::class)
class ExampleFragmentTests : TestBase() {

    @Test
    fun test_example() {

        launchFragmentInContainer<ExampleFragment>(
            themeResId = R.style.AppTheme
        ).onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mock(NavController::class.java))

            // Assert that the text is there visible on the screen
            assertThat(
                fragment.text_example_text.text.toString(),
                `is`(fragment.context?.getString(R.string.example_text))
            )
        }

        // If you're going to run a few items in a row, you can run them like this
        ExampleFragmentRobot().run {
            exampleTextViewIsDisplayed()
        }

        // If you're only accessing one item of the robot, you can access it like this (above works fine too)
        ExampleFragmentRobot().exampleTextViewIsDisplayed()
    }


}