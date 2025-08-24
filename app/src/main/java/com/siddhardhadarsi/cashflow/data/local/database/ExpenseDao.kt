package com.siddhardhadarsi.cashflow.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY timestamp DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE date = :date ORDER BY timestamp DESC")
    fun getExpensesByDate(date: String): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    fun getExpensesByDateRange(startDate: String, endDate: String): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Query("SELECT SUM(amount) FROM expenses WHERE date = :date")
    suspend fun getTotalForDate(date: String): Double?

    @Query("SELECT * FROM expenses WHERE title LIKE :query OR notes LIKE :query ORDER BY timestamp DESC")
    suspend fun searchExpenses(query: String): List<ExpenseEntity>

    @Query("SELECT * FROM expenses WHERE title = :title AND amount = :amount AND timestamp BETWEEN :startTime AND :endTime")
    suspend fun findSimilarExpenses(
        title: String,
        amount: Double,
        startTime: Long,
        endTime: Long
    ): List<ExpenseEntity>
}