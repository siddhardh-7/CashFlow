package com.siddhardhadarsi.cashflow.core.utils

import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.model.ExpenseCategory
import com.siddhardhadarsi.cashflow.domain.model.ExpenseReport

object MockDataProvider {
    fun getMockExpenses(): List<Expense> {
        return listOf(
            Expense(
                id = 1,
                title = "Office Lunch",
                amount = 450.0,
                category = ExpenseCategory.FOOD,
                notes = "Team lunch at nearby restaurant",
                timestamp = System.currentTimeMillis() - 3600000
            ),
            Expense(
                id = 2,
                title = "Uber to Client Meeting",
                amount = 280.0,
                category = ExpenseCategory.TRAVEL,
                notes = null,
                timestamp = System.currentTimeMillis() - 7200000
            ),
            Expense(
                id = 3,
                title = "Bonus for Sales Team",
                amount = 5000.0,
                category = ExpenseCategory.STAFF,
                notes = "Q4 performance bonus",
                timestamp = System.currentTimeMillis() - 10800000
            ),
            Expense(
                id = 4,
                title = "Internet Bill",
                amount = 1200.0,
                category = ExpenseCategory.UTILITY,
                notes = "Monthly broadband payment",
                timestamp = System.currentTimeMillis() - 86400000
            )
        )
    }

    fun getMockReport(): ExpenseReport {
        val dailyTotals = mapOf(
            "2024-01-15" to 1200.0,
            "2024-01-16" to 850.0,
            "2024-01-17" to 2300.0,
            "2024-01-18" to 400.0,
            "2024-01-19" to 1100.0,
            "2024-01-20" to 750.0,
            "2024-01-21" to 930.0
        )

        val categoryTotals = mapOf(
            ExpenseCategory.STAFF to 5000.0,
            ExpenseCategory.TRAVEL to 680.0,
            ExpenseCategory.FOOD to 1200.0,
            ExpenseCategory.UTILITY to 650.0
        )

        return ExpenseReport(
            dailyTotals = dailyTotals,
            categoryTotals = categoryTotals,
            totalAmount = categoryTotals.values.sum(),
            totalCount = 12,
            period = "2024-01-15 to 2024-01-21"
        )
    }
}