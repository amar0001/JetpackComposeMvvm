package com.test.medicine.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicineItem(val problems: MutableList<Problem> = mutableListOf())

@Serializable
data class Problem(
    @SerialName("Diabetes") val diabetes: List<Diabetes>? = null
)

@Serializable
data class Diabetes(
    val medications: List<Medication>,
    val labs: List<Lab>
)

@Serializable
data class Medication(val medicationsClasses: List<MedicationClass>)

@Serializable
data class MedicationClass(
    val className: List<ClassName>,
    @SerialName("className2") val className2: List<ClassName>
)

@Serializable
data class ClassName(
    @SerialName("associatedDrug") val associatedDrug: List<DrugItem>,
    @SerialName("associatedDrug#2") val associatedDrug2: List<DrugItem>
)



@Serializable
data class Lab(val missing_field: String)
