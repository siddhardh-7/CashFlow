package com.siddhardhadarsi.cashflow.data.local.database

@androidx.room.Entity(tableName = "expenses")
data class ExpenseEntity(
    @androidx.room.PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val notes: String?,
    val receiptImagePath: String?,
    val timestamp: Long,
    val date: String
)