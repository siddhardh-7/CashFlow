package com.siddhardhadarsi.cashflow.presentation.screen.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.siddhardhadarsi.cashflow.domain.model.GroupBy
import com.siddhardhadarsi.cashflow.presentation.components.ExpenseItem

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun ExpenseListScreen(
    onNavigateBack: () -> Unit,
    onNavigateToEntry: () -> Unit,
    viewModel: ExpenseListViewModel = hiltViewModel<ExpenseListViewModel>()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val groupBy by viewModel.groupBy.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = "Expenses",
                        style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = onNavigateBack) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    androidx.compose.material3.IconButton(onClick = onNavigateToEntry) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Expense"
                        )
                    }
                }
            )
        }
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Summary Card
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    androidx.compose.material3.Text(
                        text = "Date: $selectedDate",
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                    )
                    androidx.compose.foundation.layout.Spacer(
                        modifier = Modifier.height(
                            8.dp
                        )
                    )
                    androidx.compose.foundation.layout.Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                    ) {
                        androidx.compose.material3.Text(
                            text = "Total: ₹${String.format("%.2f", uiState.totalAmount)}",
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.primary
                        )
                        androidx.compose.material3.Text(
                            text = "Count: ${uiState.totalCount}",
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            // Controls
            androidx.compose.foundation.layout.Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            ) {
                androidx.compose.material3.TextButton(
                    onClick = { /* Date picker implementation */ }
                ) {
                    androidx.compose.material3.Text("Change Date")
                }
                androidx.compose.material3.TextButton(
                    onClick = { viewModel.toggleGroupBy() }
                ) {
                    androidx.compose.material3.Text("Group by ${if (groupBy == GroupBy.TIME) "Category" else "Time"}")
                }
            }

            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.height(
                    8.dp
                )
            )

            // Expense List
            when {
                uiState.isLoading -> {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        androidx.compose.material3.CircularProgressIndicator()
                    }
                }

                uiState.expenses.isEmpty() -> {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        androidx.compose.foundation.layout.Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            androidx.compose.material3.Text(
                                text = "No expenses found",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                            )
                            androidx.compose.foundation.layout.Spacer(
                                modifier = Modifier.height(
                                    8.dp
                                )
                            )
                            androidx.compose.material3.Button(onClick = onNavigateToEntry) {
                                androidx.compose.material3.Text("Add First Expense")
                            }
                        }
                    }
                }

                else -> {
                    androidx.compose.foundation.lazy.LazyColumn(
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        if (groupBy == GroupBy.CATEGORY) {
                            val groupedExpenses = uiState.expenses.groupBy { it.category }
                            groupedExpenses.forEach { (category, expenses) ->
                                item {
                                    androidx.compose.material3.Text(
                                        text = category.displayName,
                                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                        color = category.color,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                                items(expenses) { expense ->
                                    ExpenseItem(expense = expense)
                                }
                            }
                        } else {
                            items(uiState.expenses) { expense ->
                                ExpenseItem(expense = expense)
                            }
                        }
                    }
                }
            }
        }
    }
}