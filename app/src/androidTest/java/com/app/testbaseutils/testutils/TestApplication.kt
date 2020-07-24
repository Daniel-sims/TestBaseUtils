package com.app.testbaseutils.testutils

import com.app.testbaseutils.ProjectNameApplication


class TestApplication : ProjectNameApplication() {

    /***
     * Override base url to point to the mock web dispatcher
     */
    override fun getBaseUrl(): String {
        return "http://127.0.0.1:8080"
    }
}