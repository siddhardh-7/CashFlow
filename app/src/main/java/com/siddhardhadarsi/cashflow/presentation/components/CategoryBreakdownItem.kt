package com.siddhardhadarsi.cashflow.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import com.siddhardhadarsi.cashflow.domain.model.ExpenseCategory

@androidx.compose.runtime.Composable
fun CategoryBreakdownItem(
    category: ExpenseCategory,
    amount: Double,
    percentage: Double
) {
    androidx.compose.foundation.layout.Row(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier
                .size(16.dp)
                .background(category.color, androidx.compose.foundation.shape.CircleShape)
        )

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(12.dp))

        androidx.compose.foundation.layout.Column(
            modifier = androidx.compose.ui.Modifier.weight(1f)
        ) {
            androidx.compose.material3.Text(
                text = category.displayName,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )
            androidx.compose.material3.LinearProgressIndicator(
                progress = (percentage / 100).toFloat(),
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                color = category.color
            )
        }

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(12.dp))

        androidx.compose.foundation.layout.Column(
            horizontalAlignment = androidx.compose.ui.Alignment.End
        ) {
            androidx.compose.material3.Text(
                text = "₹${String.format("%.2f", amount)}",
                style = androidx.compose.material3.MaterialTheme.typography.titleSmall
            )
            androidx.compose.material3.Text(
                text = "${String.format("%.1f", percentage)}%",
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}