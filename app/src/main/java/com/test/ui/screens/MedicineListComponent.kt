package com.test.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.test.medicine.data.model.DiseaseDrugItem
import com.test.medicine.data.model.DrugItem
import com.test.medicine.presentation.MedicineViewModel
import com.test.ui.nav.Routes
import com.test.ui.widgets.ErrorMessage
import com.test.ui.widgets.ProgressLoader
import com.test.utils.getGreeting


@Composable
fun MedicineListComponent(
    navigation: NavHostController, email: String?, viewModel: MedicineViewModel,
) {

    var greetingMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        greetingMessage = "${getGreeting()}, $email!"

        // Display the greeting message
        if (greetingMessage.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = greetingMessage,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        val medicineState by viewModel.medicineState.collectAsState()

        // Using a when expression to handle different UI states
        when {
            medicineState.loading -> {
                // Show loader while loading data
                ProgressLoader()
            }

            medicineState.error != null -> {
                // Show error message if there's an error
                ErrorMessage(medicineState.error!!)
            }

            medicineState.medicineItemList!!.isNotEmpty() -> {
                ShowLazyList(viewModel, navigation, medicineState.medicineItemList!!)
            }

            else -> {
                ErrorMessage("No medicine available.")
            }
        }
    }

}

@Composable
fun ShowLazyList(
    viewModel: MedicineViewModel,
    navigation: NavHostController,
    listItems: List<DiseaseDrugItem>
) {
    // LazyColumn to display the items
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listItems.size) { index ->

            val item = listItems[index]

            Text(
                text = item.diseaseName,
                modifier = Modifier.padding(8.dp),
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
            )

            // Show a list of drugs (limit to 3 or 4 drugs)
            item.drugs.take(4).forEach { drug ->
                Column(Modifier.clickable {
                    viewModel.savedItem = drug
                    viewModel.diseaseName = item.diseaseName
                    navigation.navigate(route = Routes.MEDICINE_DETAILS_SCREEN)
                }) {
                    DrugCard(drug)
                }

            }

        }
    }
}

@Composable
fun DrugCard(drugItem: DrugItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TextListWidget("Drug Name: ${drugItem.name}")
            TextListWidget("Drug Name: ${drugItem.dose}")
            TextListWidget("Drug Name: ${drugItem.strength}")

        }
    }
}


@Preview
@Composable
fun PreviewDiseaseDrugList() {

}
