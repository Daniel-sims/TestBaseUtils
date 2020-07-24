package com.app.testbaseutils.testutils.base

import androidx.annotation.IdRes
import androidx.annotation.IntegerRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.app.testbaseutils.testutils.actions.recyclerview.RecyclerViewItemCountAssertion

open class InteractionsTestBase {

    /**
     * Checks to see if the ID is displayed on the screen. This is simply a helper method to make
     * it clear what you're doing, typically set an ID on the parent layout to check.
     *
     * @param fragmentId Resource ID of the parent layout.
     */
    protected fun currentFragmentIs(@IntegerRes fragmentId: Int): ViewInteraction =
        onView(withId(fragmentId)).check(matches(isDisplayed()))

    /**
     * Clicks view on the UI with the id [resId].
     *
     * @param resId Resource ID on the view to press.
     */
    protected fun clickView(@IntegerRes resId: Int): ViewInteraction =
        onView(withId(resId)).perform(click())

    /**
     * Checks to see if there's a SnackBar on the UI with the visible message [message].
     *
     * @param message Message to check for in the SnackBar.
     */
    protected fun checkSnackBarWithMessageIsDisplayed(
        message: String
    ): ViewInteraction = onView(withId(com.google.android.material.R.id.snackbar_text))
        .check(matches(withText(message)))

    /**
     * Check to see if a view with the [resId] is displayed for the UI to see.
     *
     * Note this will be valid if the view is only partially displayed.
     *
     * @param resId Resource ID of the view.
     */
    protected fun checkViewIsDisplayed(@IntegerRes resId: Int): ViewInteraction =
        onView(withId(resId)).check(matches(isDisplayed()))

    /**
     * Check to see if a view with the [resId] is completely displayed for the UI to see.
     *
     * @param resId Resource ID of the view.
     */
    protected fun checkViewIsCompletelyDisplayed(@IntegerRes resId: Int): ViewInteraction =
        onView(withId(resId)).check(matches(isCompletelyDisplayed()))

    /**
     * Checks to see if the [toolbarId] has a title with the text [title].
     *
     * @param toolbarId Toolbar to check.
     * @param title Title text to check for.
     */
    protected fun toolbarHasTitle(@IdRes toolbarId: Int, title: String): ViewInteraction =
        onView(withId(toolbarId)).check(matches(hasDescendant(withText(title))))

    /**
     * Checks the recyclerview item count for the recyclerview associated with [resId].
     *
     * @param resId Resource ID for the recyclerview
     * @param expectedCount Expected number of items in the recyclerview.
     */
    protected fun checkRecyclerViewItemCount(
        @IntegerRes resId: Int,
        expectedCount: Int
    ): ViewInteraction =
        onView(withId(resId)).check(RecyclerViewItemCountAssertion(expectedCount))

    /**
     * Clicks a recyclerview item with the text [text].
     *
     * @param resId Resource ID for the recyclerview.
     * @param text Text to look for in the recyclerview item.
     */
    protected fun selectRecyclerViewItemWithText(
        @IntegerRes resId: Int,
        text: String
    ): ViewInteraction =
        onView(withId(resId)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(text)),
                click()
            )
        )

}