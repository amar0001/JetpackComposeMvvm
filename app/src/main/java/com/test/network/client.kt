package com.test.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging

val client = HttpClient {
    install(Logging) {
        level = LogLevel.ALL
    }
}