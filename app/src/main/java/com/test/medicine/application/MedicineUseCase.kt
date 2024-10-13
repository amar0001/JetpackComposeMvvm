package com.test.medicine.application

import com.test.medicine.data.MedicalRepository
import com.test.medicine.data.model.DiseaseDrugItem
import com.test.medicine.data.model.DrugItem
import com.test.medicine.data.model.Medication
import com.test.medicine.data.model.MedicineItem
import com.test.network.ApiResponse
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class GetMedicalDataUseCase @Inject constructor(private val repository: MedicalRepository) {

    suspend operator fun invoke(): List<DiseaseDrugItem> {

        val diseaseDrugList: MutableList<DiseaseDrugItem>

        val response = repository.getMedicalData(true)

        diseaseDrugList = when (response) {

            is ApiResponse.Error -> {

                emptyList<DiseaseDrugItem>().toMutableList()
            }

            is ApiResponse.Success -> {

                getArray(response.data)

            }


        }

        return diseaseDrugList
    }


    private fun getArray(data: MedicineItem): MutableList<DiseaseDrugItem> {
        val diseaseDrugList = mutableListOf<DiseaseDrugItem>()

        data.problems.forEach { problem ->
            // Use reflection to dynamically extract all diseases (key names) from the problem
            problem::class.memberProperties.forEach { property -> // Use memberProperties to filter only properties
                // Check if the property is a List of diseases (e.g., Diabetes, Asthma)
                val diseaseData = property.getter.call(problem) as? List<*>

                diseaseData?.let { list ->
                    list.forEach { disease ->
                        val drugs = mutableListOf<DrugItem>()

                        // Access the medications within each disease dynamically
                        val medications =
                            disease!!::class.memberProperties.find { it.name == "medications" }
                                ?.getter?.call(disease) as? List<Medication>

                        medications?.forEach { medication ->
                            medication.medicationsClasses.forEach { medicationClass ->
                                // Collect drugs from className
                                medicationClass.className.forEach { className ->
                                    drugs.addAll(className.associatedDrug)
                                    drugs.addAll(className.associatedDrug2)
                                }
                                // Collect drugs from className2
                                medicationClass.className2.forEach { className2 ->
                                    drugs.addAll(className2.associatedDrug)
                                    drugs.addAll(className2.associatedDrug2)
                                }
                            }
                        }

                        // Add the disease name and its associated drugs
                        diseaseDrugList.add(DiseaseDrugItem(property.name, drugs))
                    }
                }
            }
        }
        return diseaseDrugList
    }
}
