package com.test.medicine.presentation

import com.test.medicine.data.model.DiseaseDrugItem


data class MedicineState (
    val medicineItemList: List<DiseaseDrugItem>? = null,
    val loading: Boolean = false,
    val error: String? = null
)
