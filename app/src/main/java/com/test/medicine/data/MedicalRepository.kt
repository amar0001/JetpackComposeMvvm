package com.test.medicine.data

import com.test.medicine.data.model.MedicineItem
import com.test.network.ApiResponse

interface MedicalRepository {
    suspend fun getMedicalData(boolean: Boolean): ApiResponse<MedicineItem>
  //  suspend fun saveMedicalData(medicalData: MedicineItem)
}