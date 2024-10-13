package com.test.medicine.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.medicine.data.model.Problem

@Dao
interface  MedicalDataDao {

    @Query("SELECT * FROM medical_data")
    suspend fun getAllMedicalData(): List<MedicalDataEntity>

    @Query("SELECT * FROM medical_data WHERE id = :id")
    suspend fun getMedicalData(id: Int): MedicalDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedicalData(problems: MedicalDataEntity)

    @Query("DELETE FROM medical_data")
    suspend fun deleteALL()
}