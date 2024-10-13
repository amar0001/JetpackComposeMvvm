package com.test.medicine.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.test.medicine.data.model.DrugItem
import com.test.medicine.data.model.Problem

@Entity(tableName = "medical_data")
data class MedicalDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val problems: String
)