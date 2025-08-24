package com.siddhardhadarsi.cashflow.presentation.components

import androidx.compose.foundation.layout.fillMaxSize

@androidx.compose.runtime.Composable
fun LoadingScreen() {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.CircularProgressIndicator()
    }
}