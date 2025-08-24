package com.siddhardhadarsi.cashflow.data.repository

import com.siddhardhadarsi.cashflow.core.utils.DateUtils
import com.siddhardhadarsi.cashflow.data.local.database.ExpenseDao
import com.siddhardhadarsi.cashflow.data.mapper.ExpenseMapper
import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.model.ExpenseReport
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao,
    private val mapper: ExpenseMapper
) : ExpenseRepository {

    override fun getAllExpenses(): Flow<List<Expense>> {
        return dao.getAllExpenses().map { entities ->
            entities.map { mapper.entityToDomain(it) }
        }
    }

    override fun getExpensesByDate(date: String): Flow<List<Expense>> {
        return dao.getExpensesByDate(date).map { entities ->
            entities.map { mapper.entityToDomain(it) }
        }
    }

    override fun getExpensesByDateRange(startDate: String, endDate: String): Flow<List<Expense>> {
        return dao.getExpensesByDateRange(startDate, endDate).map { entities ->
            entities.map { mapper.entityToDomain(it) }
        }
    }

    override suspend fun insertExpense(expense: Expense): Long {
        return dao.insertExpense(mapper.domainToEntity(expense))
    }

    override suspend fun deleteExpense(expense: Expense) {
        dao.deleteExpense(mapper.domainToEntity(expense))
    }

    override suspend fun getTotalForDate(date: String): Double {
        return dao.getTotalForDate(date) ?: 0.0
    }

    override suspend fun generateReport(days: Int): ExpenseReport {
        val endDate = DateUtils.getCurrentDate()
        val startDate = DateUtils.getDateDaysAgo(days - 1)

        val expenses = getExpensesByDateRange(startDate, endDate).first()

        val dailyTotals = expenses.groupBy { it.date }
            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }

        val categoryTotals = expenses.groupBy { it.category }
            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }

        return ExpenseReport(
            dailyTotals = dailyTotals,
            categoryTotals = categoryTotals,
            totalAmount = expenses.sumOf { it.amount },
            totalCount = expenses.size,
            period = "$startDate to $endDate"
        )
    }

    override suspend fun isDuplicateExpense(expense: Expense): Boolean {
        val timeWindow = 3600000L // 1 hour
        val startTime = expense.timestamp - timeWindow
        val endTime = expense.timestamp + timeWindow

        return dao.findSimilarExpenses(
            title = expense.title,
            amount = expense.amount,
            startTime = startTime,
            endTime = endTime
        ).isNotEmpty()
    }

    override suspend fun searchExpenses(query: String): List<Expense> {
        return dao.searchExpenses("%$query%").map { mapper.entityToDomain(it) }
    }
}