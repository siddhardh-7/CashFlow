package com.siddhardhadarsi.cashflow.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.composable
import com.siddhardhadarsi.cashflow.presentation.screen.entry.ExpenseEntryScreen
import com.siddhardhadarsi.cashflow.presentation.screen.list.ExpenseListScreen
import com.siddhardhadarsi.cashflow.presentation.screen.report.ExpenseReportScreen

@androidx.compose.runtime.Composable
fun ExpenseTrackerApp() {
    val navController = androidx.navigation.compose.rememberNavController()

    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        androidx.navigation.compose.NavHost(
            navController = navController,
            startDestination = "entry"
        ) {
            composable("entry") {
                ExpenseEntryScreen(
                    onNavigateToList = { navController.navigate("list") },
                    onNavigateToReport = { navController.navigate("report") }
                )
            }
            composable("list") {
                ExpenseListScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToEntry = { navController.navigate("entry") }
                )
            }
            composable("report") {
                ExpenseReportScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}