package com.siddhardhadarsi.cashflow.presentation.screen.report

import com.siddhardhadarsi.cashflow.domain.model.ExpenseReport

data class ExpenseReportUiState(
    val report: ExpenseReport? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showExportSuccess: Boolean = false
)