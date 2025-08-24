package com.siddhardhadarsi.cashflow.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import com.siddhardhadarsi.cashflow.domain.model.Expense

@androidx.compose.runtime.Composable
fun ExpenseItem(expense: Expense) {
    androidx.compose.material3.Card(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth()
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = androidx.compose.ui.Modifier.padding(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            androidx.compose.foundation.layout.Box(
                modifier = androidx.compose.ui.Modifier
                    .size(12.dp)
                    .background(
                        expense.category.color,
                        androidx.compose.foundation.shape.CircleShape
                    )
            )
            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(12.dp))
            androidx.compose.foundation.layout.Column(modifier = androidx.compose.ui.Modifier.weight(1f)) {
                androidx.compose.material3.Text(
                    text = expense.title,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
                androidx.compose.material3.Text(
                    text = expense.category.displayName,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
                expense.notes?.let {
                    androidx.compose.material3.Text(
                        text = it,
                        style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            androidx.compose.material3.Text(
                text = "₹${String.format("%.2f", expense.amount)}",
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )
        }
    }
}