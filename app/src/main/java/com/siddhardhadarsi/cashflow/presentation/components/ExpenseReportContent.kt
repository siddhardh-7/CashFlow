package com.siddhardhadarsi.cashflow.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.siddhardhadarsi.cashflow.domain.model.ExpenseReport

@androidx.compose.runtime.Composable
fun ExpenseReportContent(
    report: ExpenseReport,
    onExport: () -> Unit,
    showExportSuccess: Boolean
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Summary Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "7-Day Summary",
                        style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                    )
                    Spacer(
                        modifier = Modifier.height(
                            8.dp
                        )
                    )
                    Text(
                        text = "Period: ${report.period}",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                    Spacer(
                        modifier = Modifier.height(
                            8.dp
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Total Amount",
                                style = androidx.compose.material3.MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = "₹${String.format("%.2f", report.totalAmount)}",
                                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
                            )
                        }
                        Column {
                            Text(
                                text = "Total Expenses",
                                style = androidx.compose.material3.MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = "${report.totalCount}",
                                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }

        // Daily Totals Chart (Mock)
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Daily Spending",
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                    )
                    Spacer(
                        modifier = Modifier.height(
                            16.dp
                        )
                    )

                    // Mock Bar Chart
                    DailySpendingChart(dailyTotals = report.dailyTotals)
                }
            }
        }

        // Category Breakdown
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Category Breakdown",
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                    )
                    Spacer(
                        modifier = Modifier.height(
                            16.dp
                        )
                    )

                    report.categoryTotals.forEach { (category, amount) ->
                        CategoryBreakdownItem(
                            category = category,
                            amount = amount,
                            percentage = if (report.totalAmount > 0) (amount / report.totalAmount) * 100 else 0.0
                        )
                        Spacer(
                            modifier = Modifier.height(
                                8.dp
                            )
                        )
                    }
                }
            }
        }

        // Export Button
        item {
            Column {
                androidx.compose.material3.Button(
                    onClick = onExport,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Send,
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier.width(
                            8.dp
                        )
                    )
                    Text("Export Report")
                }

                if (showExportSuccess) {
                    Spacer(
                        modifier = Modifier.height(
                            8.dp
                        )
                    )
                    Card(
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.Green
                            )
                            Spacer(
                                modifier = Modifier.width(
                                    8.dp
                                )
                            )
                            Text("Report exported successfully!")
                        }
                    }
                }
            }
        }
    }
}