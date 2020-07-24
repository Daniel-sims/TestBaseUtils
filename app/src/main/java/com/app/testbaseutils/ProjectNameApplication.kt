package com.app.testbaseutils

import android.app.Application

open class ProjectNameApplication  : Application() {

    /***
     * Returns the base url for the application to use to call the API.
     *
     * @return The base url to use as the base of a API call.
     */
    open fun getBaseUrl(): String {
        return "BaseURL.com"
    }
}