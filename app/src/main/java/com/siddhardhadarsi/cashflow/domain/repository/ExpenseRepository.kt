package com.siddhardhadarsi.cashflow.domain.repository

import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.model.ExpenseReport
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getAllExpenses(): Flow<List<Expense>>

    fun getExpensesByDate(date: String): Flow<List<Expense>>

    fun getExpensesByDateRange(startDate: String, endDate: String): Flow<List<Expense>>

    suspend fun insertExpense(expense: Expense): Long

    suspend fun deleteExpense(expense: Expense)

    suspend fun getTotalForDate(date: String): Double

    suspend fun generateReport(days: Int = 7): ExpenseReport

    suspend fun isDuplicateExpense(expense: Expense): Boolean

    suspend fun searchExpenses(query: String): List<Expense>
}