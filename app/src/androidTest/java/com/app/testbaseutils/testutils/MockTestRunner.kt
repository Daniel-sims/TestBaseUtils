package com.app.testbaseutils.testutils

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.test.runner.AndroidJUnitRunner

/***
 * Mock test runner that allows us to access the [TestApplication].
 */
class MockTestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    /***
     * Return the TestApplication as the application, doing this allows us to access the getBaseUrl()
     * method that returns the BASE_URL of the mock web server.
     */
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }

}