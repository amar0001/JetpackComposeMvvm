package com.test.medicine.data

import com.test.medicine.data.database.MedicalDataDao
import com.test.medicine.data.model.MedicineItem
import com.test.medicine.data.model.Problem
import com.test.medicine.data.network.MedicineService
import com.test.network.ApiResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MedicalRepositoryImpl @Inject constructor(
    private val apiService: MedicineService,
    private val dao: MedicalDataDao
) : MedicalRepository {

    override suspend fun getMedicalData(forceFetch: Boolean): ApiResponse<MedicineItem> {

//        if (forceFetch) {
//            dao.deleteALL()
//            return apiService.fetchMedicine()
//        }

        val localData = dao.getAllMedicalData()


        return if (localData.isEmpty()) {

            fetchMedicine()
        } else {

            println("localData$localData")
            val medicineItems = MedicineItem(mutableListOf())
            localData.forEachIndexed { index, medicalDataEntity ->

              val  problemItem = deserializeMedicine(medicalDataEntity.problems)
                medicineItems.problems.add(problemItem)
            }
            ApiResponse.Success(medicineItems)

        }

    }

    // Deserializing function
    fun deserializeMedicine(json: String): Problem {
        val data: Problem = Json { ignoreUnknownKeys = true }.decodeFromString(json)
        return data
    }


    private suspend fun fetchMedicine(): ApiResponse<MedicineItem> {
        return apiService.fetchMedicine()
    }


}
