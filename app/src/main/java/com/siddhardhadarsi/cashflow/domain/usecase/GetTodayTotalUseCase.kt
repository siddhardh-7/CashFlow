package com.siddhardhadarsi.cashflow.domain.usecase

import com.siddhardhadarsi.cashflow.core.utils.DateUtils
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*class GetTodayTotalUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    data class TodayTotalData(
        val total: Double,
        val count: Int,
        val date: String,
        val formattedTotal: String
    )

    operator fun invoke(): Flow<TodayTotalData> = flow {
        val today = DateUtils.getCurrentDate()
        val total = repository.getTotalForDate(today)
        val expenses = repository.getExpensesByDate(today)

        expenses.collect { expenseList ->
            emit(TodayTotalData(
                total = total,
                count = expenseList.size,
                date = today,
                formattedTotal = "₹${String.format("%.2f", total)}"
            ))
        }
    }

    suspend fun getTotalForDate(date: String): Double {
        return repository.getTotalForDate(date)
    }

    suspend fun getTotalForCurrentWeek(): Double {
        val dates = DateUtils.getLastNDays(7)
        return dates.sumOf { date ->
            repository.getTotalForDate(date)
        }
    }

    suspend fun getTotalForCurrentMonth(): Double {
        val dates = DateUtils.getLastNDays(30)
        return dates.sumOf { date ->
            repository.getTotalForDate(date)
        }
    }
}*/

class GetTodayTotalUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    operator fun invoke(): Flow<Double> = flow {
        val today = DateUtils.getCurrentDate()
        val total = repository.getTotalForDate(today)
        emit(total)
    }
}