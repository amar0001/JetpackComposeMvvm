package com.test.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import javax.inject.Inject

class MyHttpRequest<T> @Inject constructor(
    private val httpClient: HttpClient,
    private val deserialize: (String) -> T
) {

    suspend fun request(url: String): ApiResponse<T> {


        return try {
            val response: HttpResponse = httpClient.get(url)


            // Check if the response is OK
            if (response.status == HttpStatusCode.OK) {
                // Read the raw JSON response as a string
                val jsonResponse = response.bodyAsText()

                // Deserialize the JSON response into the expected type
                val item: T = deserialize(jsonResponse)

                ApiResponse.Success(item) // Return success response
            } else {
                ApiResponse.Error(Exception("Non-200 Response: ${response.status}"))
            }
        } catch (e: Exception) {

            println("Exception$e")
            ApiResponse.Error(e) // Return error response
        }
    }
}
