package com.siddhardhadarsi.cashflow.domain.usecase

import com.siddhardhadarsi.cashflow.core.utils.DateUtils
import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.model.GroupBy
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    data class ExpenseListData(
        val expenses: List<Expense>,
        val totalAmount: Double,
        val totalCount: Int,
        val groupedExpenses: Map<String, List<Expense>>
    )

    operator fun invoke(
        date: String = DateUtils.getCurrentDate(),
        groupBy: GroupBy = GroupBy.TIME
    ): Flow<ExpenseListData> {
        return repository.getExpensesByDate(date).map { expenses ->
            val grouped = when (groupBy) {
                GroupBy.TIME -> expenses.groupBy {
                    DateUtils.formatTime(it.timestamp)
                }
                GroupBy.CATEGORY -> expenses.groupBy {
                    it.category.displayName
                }
            }

            ExpenseListData(
                expenses = expenses,
                totalAmount = expenses.sumOf { it.amount },
                totalCount = expenses.size,
                groupedExpenses = grouped
            )
        }
    }
}