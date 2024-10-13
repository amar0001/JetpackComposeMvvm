package com.test.medicine.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.medicine.application.GetMedicalDataUseCase
import com.test.medicine.data.model.DiseaseDrugItem
import com.test.medicine.data.model.DrugItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getMedicalDataUseCase: GetMedicalDataUseCase
) : ViewModel() {

    var savedItem: DrugItem?  = null
    var diseaseName: String?  = null
    private val _medicineState: MutableStateFlow<MedicineState> =
        MutableStateFlow(MedicineState(loading = true))

    val medicineState: StateFlow<MedicineState> get() = _medicineState

    init {
        getMedicine(true)
    }


    fun getMedicine(forceFetch: Boolean = false) {

        viewModelScope.launch {
            _medicineState.emit(MedicineState(loading = true))
            val list = getMedicalDataUseCase
            _medicineState.emit(MedicineState(loading = false, medicineItemList = list.invoke()))
        }
    }


}