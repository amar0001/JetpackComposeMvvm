package com.test.medicine.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DrugItem(
    val name: String,
    val dose: String?,
    val strength: String?
)