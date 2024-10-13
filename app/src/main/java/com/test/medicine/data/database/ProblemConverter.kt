package com.test.medicine.data.database

import androidx.room.TypeConverter
import com.test.medicine.data.model.Problem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProblemConverter {

    @TypeConverter
    fun fromProblemList(problems: List<Problem>): String {
        return Json.encodeToString(problems) // Convert list to JSON string
    }

    @TypeConverter
    fun toProblemList(json: String): List<Problem> {
        return Json.decodeFromString(json) // Convert JSON string back to list
    }
}