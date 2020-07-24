package com.app.testbaseutils.testutils.actions.recyclerview

import android.view.View
import androidx.annotation.IntegerRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/***
 * Checks to see if the recyclerview's adapter has the correct amount of items.
 *
 * @param expectedCount Expected amount of items to be in the recyclerview
 *
 * @throws Throws an exception if the recyclerviews item count does not match the expectionCount parameter
 */
class RecyclerViewItemCountAssertion(
    private val expectedCount: Int
) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        assertThat((view as RecyclerView).adapter?.itemCount, Matchers.equalTo(expectedCount))
    }
}