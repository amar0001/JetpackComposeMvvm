package com.test.ui.nav

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.medicine.presentation.MedicineViewModel
import com.test.ui.screens.LoginComponent
import com.test.ui.screens.MedicineDetailsComponent
import com.test.ui.screens.MedicineListComponent


@Composable
fun MyAppNavHost(activity: Activity) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MedicineViewModel>()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN_SCREEN
    ) {

        composable(Routes.LOGIN_SCREEN) {
            LoginComponent(navigation = navController)
        }

        composable(
            route = "${Routes.MEDICINE_LIST_SCREEN}/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            // Retrieve the email argument from backStackEntry
            val email = backStackEntry.arguments?.getString("email")
            MedicineListComponent(navigation = navController, email = email, viewModel)
        }



        composable(Routes.MEDICINE_DETAILS_SCREEN) {
            MedicineDetailsComponent(navigation = navController,viewModel)
        }

    }
}