package com.app.testbaseutils.tests.integration

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.app.testbaseutils.ExampleActivity
import com.app.testbaseutils.ExampleFragment
import com.app.testbaseutils.robots.ExampleFragmentRobot
import com.app.testbaseutils.testutils.base.IntegrationTestBase
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExampleIntegrationTest : IntegrationTestBase<ExampleActivity>(ExampleActivity::class.java) {

    @Test
    fun test_example_integration() {
        ExampleFragmentRobot().exampleTextViewIsDisplayed()
    }


}