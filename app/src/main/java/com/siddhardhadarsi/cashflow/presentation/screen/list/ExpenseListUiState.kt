package com.siddhardhadarsi.cashflow.presentation.screen.list

import com.siddhardhadarsi.cashflow.domain.model.Expense

data class ExpenseListUiState(
    val expenses: List<Expense> = emptyList(),
    val totalAmount: Double = 0.0,
    val totalCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)