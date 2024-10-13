package com.test.medicine.data.network

import com.test.medicine.data.database.MedicalDataDao
import com.test.medicine.data.database.MedicalDataEntity
import com.test.medicine.data.model.MedicineItem
import com.test.network.ApiResponse
import com.test.network.MyHttpRequest
import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.json.JSONObject
import javax.inject.Inject

class MedicineService @Inject constructor(
    private val httpClient: HttpClient,
    private val dao: MedicalDataDao
) {

    private val questionsRequest = MyHttpRequest(httpClient, ::fetchAndDeserializeMedicine)
    suspend fun fetchMedicine(): ApiResponse<MedicineItem> {

        val url = "https://run.mocky.io/v3/ddbece86-f274-4317-a536-1b0a4d7293ef"
        return questionsRequest.request(url)
    }

    // Use a regular function to wrap the suspend function
    private fun fetchAndDeserializeMedicine(json: String): MedicineItem {

        return runBlocking {
            deserializeMedicine(json)
        }
    }

    private suspend  fun deserializeMedicine(json: String): MedicineItem {
        saveProblemsToDatabase(json)
        return Json { ignoreUnknownKeys = true }.decodeFromString(json)
    }

     private suspend fun saveProblemsToDatabase(json: String) {
        val jsonObject = JSONObject(json)
        val problemsArray = jsonObject.getJSONArray("problems")

        for (i in 0 until problemsArray.length()) {
            val problemString =
                problemsArray.getJSONObject(i).toString()
            val medicalDataEntity = MedicalDataEntity(problems =  problemString)
            dao.saveMedicalData(medicalDataEntity)
        }

    }
    }