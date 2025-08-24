package com.siddhardhadarsi.cashflow.domain.model

data class Expense(
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val category: ExpenseCategory,
    val notes: String? = null,
    val receiptImagePath: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val date: String = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        .format(java.util.Date(timestamp))
)
