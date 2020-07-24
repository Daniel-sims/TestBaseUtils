package com.app.testbaseutils.testutils.base

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import org.junit.Rule

open class IntegrationTestBase<T : Activity>(clz: Class<T>) : TestBase() {

    @get:Rule
    val activityRule = ActivityTestRule(clz)

}