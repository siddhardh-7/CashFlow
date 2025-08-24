package com.siddhardhadarsi.cashflow.domain.usecase

import com.siddhardhadarsi.cashflow.core.utils.DateUtils
import com.siddhardhadarsi.cashflow.domain.model.ExpenseReport
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import javax.inject.Inject

/*class GenerateReportUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    sealed class Result {
        data class Success(val report: ExpenseReport) : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(days: Int = 7): Result {
        return try {
            val report = repository.generateReport(days)
            Result.Success(report)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Failed to generate report")
        }
    }

    suspend fun getWeeklyReport(): Result = invoke(7)
    suspend fun getMonthlyReport(): Result = invoke(30)
    suspend fun getCustomReport(days: Int): Result = invoke(days)

    suspend fun exportReportData(report: ExpenseReport): String {
        return buildString {
            appendLine("CashFlow Pro - Expense Report")
            appendLine("Period: ${report.period}")
            appendLine("Generated: ${DateUtils.getCurrentDate()}")
            appendLine()

            appendLine("SUMMARY:")
            appendLine("Total Amount: ₹${String.format("%.2f", report.totalAmount)}")
            appendLine("Total Expenses: ${report.totalCount}")
            appendLine()

            appendLine("DAILY BREAKDOWN:")
            report.dailyTotals.forEach { (date, amount) ->
                appendLine("$date: ₹${String.format("%.2f", amount)}")
            }
            appendLine()

            appendLine("CATEGORY BREAKDOWN:")
            report.categoryTotals.forEach { (category, amount) ->
                val percentage = if (report.totalAmount > 0)
                    (amount / report.totalAmount) * 100 else 0.0
                appendLine("${category.displayName}: ₹${String.format("%.2f", amount)} (${String.format("%.1f", percentage)}%)")
            }
        }
    }
}*/

class GenerateReportUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    sealed class Result {
        data class Success(val report: ExpenseReport) : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(days: Int = 7): Result {
        return try {
            val report = repository.generateReport(days)
            Result.Success(report)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Failed to generate report")
        }
    }
}