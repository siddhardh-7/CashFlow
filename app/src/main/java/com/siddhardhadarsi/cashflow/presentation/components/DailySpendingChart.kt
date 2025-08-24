package com.siddhardhadarsi.cashflow.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp

@androidx.compose.runtime.Composable
fun DailySpendingChart(dailyTotals: Map<String, Double>) {
    val maxAmount = dailyTotals.values.maxOrNull() ?: 0.0

    androidx.compose.foundation.layout.Column {
        dailyTotals.toList().takeLast(7).forEach { (date, amount) ->
            androidx.compose.foundation.layout.Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                androidx.compose.material3.Text(
                    text = date.substring(5), // Show MM-DD
                    modifier = androidx.compose.ui.Modifier.width(50.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )

                val barWidth = if (maxAmount > 0) (amount / maxAmount) else 0.0
                androidx.compose.foundation.layout.Box(
                    modifier = androidx.compose.ui.Modifier
                        .height(20.dp)
                        .fillMaxWidth(barWidth.toFloat())
                        .background(
                            androidx.compose.material3.MaterialTheme.colorScheme.primary,
                            androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                        )
                )

                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                androidx.compose.material3.Text(
                    text = "₹${String.format("%.0f", amount)}",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}