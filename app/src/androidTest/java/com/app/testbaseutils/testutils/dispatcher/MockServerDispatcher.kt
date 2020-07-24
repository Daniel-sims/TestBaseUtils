package com.app.testbaseutils.testutils.dispatcher

import android.content.Context
import com.app.testbaseutils.testutils.utils.AssetReaderUtil
import com.app.testbaseutils.testutils.utils.AssetReaderUtil.asset
import com.app.testbaseutils.testutils.constants.GENERIC_ERROR_RESPONSE
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

/***
 * Network Response Codes
 */
const val UNAUTHORISED_401 = 401
const val OK_200 = 200
const val CREATED_201 = 201
const val DELETED_204 = 204
const val BAD_REQUEST_400 = 400

/***
 * HTTP Methods
 */
enum class HttpMethod(val value: String) {
    GET("GET"),
    POST("POST"),
    PATCH("PATCH"),
    DELETE("DELETE")
}

/***
 * MockServerDispatcher to intercept requests made to services that are pointed to:
 * http://127.0.0.1:8080 (Port is set when starting the MockWebServer()
 *
 * THe RequestDispatcher receives the request which contains the path, the class looks in a map
 * of responses for the request and returns the set response/default response for the URL.
 */
internal class MockServerDispatcher(
    private val context: Context
) {
    private val responses: ArrayList<MockServerResponseWrapper> = getDefaultResponses()

    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return getResponseForUrl(
                url = request.path,
                requestMethod = request.method
            )
        }
    }

    /***
     * Gets the response from the map for the URL. If the response isn't in the map, a generic
     * error response is returned.
     *
     * @param url The URL to get the response for.
     *
     * @return [MockResponse] associated with the URL or a generic 400 response.
     */
    private fun getResponseForUrl(url: String?, requestMethod: String?): MockResponse {
        return responses.firstOrNull {
            it.httpMethod.value.toLowerCase() == requestMethod?.toLowerCase() &&
                    it.url.toLowerCase() == url?.toLowerCase()
        }?.getMockResponse() ?: return MockResponse()
            .setResponseCode(400)
            .setBody(
                asset(
                    context,
                    GENERIC_ERROR_RESPONSE
                )
            )
    }

    /***
     * Sets a custom response for the dispatcher to return when the [url] is called. If the
     * response already exists (url + httpMethod) it will replace the existing one.
     *
     * @param url The URL to add/update in the map.
     * @param responseCode The code to return in the response from the URL.
     * @param responseBody The json file to return as the response from the request.
     *                     File must sit in assets/json_test_responses/XXX.json
     * @param httpMethod The HTTP Method to mock the response for; GET, POST etc..
     */
    fun setResponseForUrl(
        url: String,
        responseCode: Int = OK_200,
        responseBody: String = GENERIC_ERROR_RESPONSE,
        httpMethod: HttpMethod

    ) {
        // If there's already one in there, remove it as we're overriding it
        responses.firstOrNull {
            it.httpMethod == httpMethod && it.url.toLowerCase() == url.toLowerCase()
        }?.let {
            responses.remove(it)
        }

        responses.add(
            MockServerResponseWrapper(
                url = url,
                responseCode = responseCode,
                responseBody = AssetReaderUtil.asset(
                    context,
                    responseBody
                ),
                httpMethod = httpMethod
            )
        )
    }

    /***
     * Default responses for URLs in the map.
     *
     * All default responses will be successful.
     *
     * @return A list of default KVP of URL/MockResponse.
     */
    private fun getDefaultResponses() = arrayListOf<MockServerResponseWrapper>().apply {

        // Example
//        add(
//            MockServerResponseWrapper(
//                url = "Example/Url/",
//                responseCode = OK_200,
//                responseBody = asset(
//                    context,
//                    "Example.json"
//                ),
//                httpMethod = HttpMethod.DELETE
//            )
//        )

    }

    private inner class MockServerResponseWrapper(
        val url: String,
        val responseCode: Int,
        val responseBody: String,
        val httpMethod: HttpMethod
    ) {
        fun getMockResponse(): MockResponse =
            MockResponse().setResponseCode(responseCode).setBody(responseBody)
    }
}