package com.app.testbaseutils.testutils.base

import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.app.testbaseutils.testutils.dispatcher.HttpMethod
import com.app.testbaseutils.testutils.dispatcher.MockServerDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

open class TestBase {

    private var mockWebServer = MockWebServer()

    private lateinit var mockServerDispatcher: MockServerDispatcher

    @Before
    fun setUp() {

        mockServerDispatcher =
            MockServerDispatcher(
                InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
            )

        mockWebServer.start(8080)
        mockWebServer.dispatcher = mockServerDispatcher.RequestDispatcher()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    fun setResponseForUrl(
        url: String,
        responseCode: Int,
        responseBody: String,
        httpMethod: HttpMethod
    ) {
        mockServerDispatcher.setResponseForUrl(
            url = url,
            responseCode = responseCode,
            responseBody = responseBody,
            httpMethod = httpMethod
        )
    }
}