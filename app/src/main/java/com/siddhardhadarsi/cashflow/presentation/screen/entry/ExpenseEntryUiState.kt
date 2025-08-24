package com.siddhardhadarsi.cashflow.presentation.screen.entry

import com.siddhardhadarsi.cashflow.domain.model.ExpenseCategory

data class ExpenseEntryUiState(
    val title: String = "",
    val amount: String = "",
    val selectedCategory: ExpenseCategory = ExpenseCategory.STAFF,
    val notes: String = "",
    val isLoading: Boolean = false,
    val showSuccess: Boolean = false,
    val error: String? = null,
    val titleError: String? = null,
    val amountError: String? = null
)