package com.app.testbaseutils.robots

import com.app.testbaseutils.ExampleFragment
import com.app.testbaseutils.R
import com.app.testbaseutils.testutils.base.InteractionsTestBase

class ExampleFragmentRobot : InteractionsTestBase() {

    fun run(func: ExampleFragmentRobot.() -> Unit) = ExampleFragmentRobot().apply { func() }


    fun exampleTextViewIsDisplayed() = checkViewIsDisplayed(R.id.text_example_text)
}