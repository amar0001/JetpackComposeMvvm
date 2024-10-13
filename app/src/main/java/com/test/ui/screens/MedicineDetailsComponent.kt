package com.test.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.test.medicine.presentation.MedicineViewModel

@Composable
fun MedicineDetailsComponent(
    navigation: NavHostController,
    viewModel: MedicineViewModel,
) {

    val drugItem = viewModel.savedItem
    val diseaseName = viewModel.diseaseName


    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(Modifier.height(60.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Disease Name: ${diseaseName}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(Modifier.height(30.dp))
                drugItem?.let {
                    TextListWidget("Drug Name: ${it.name}")
                    TextListWidget("Drug Name: ${it.dose}")
                    TextListWidget("Drug Name: ${it.strength}")

                }

            }
        }

    }
}