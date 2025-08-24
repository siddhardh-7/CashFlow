package com.siddhardhadarsi.cashflow.domain.model

data class ExpenseReport(
    val dailyTotals: Map<String, Double>,
    val categoryTotals: Map<ExpenseCategory, Double>,
    val totalAmount: Double,
    val totalCount: Int,
    val period: String
)