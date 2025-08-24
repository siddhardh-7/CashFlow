package com.siddhardhadarsi.cashflow.domain.usecase

import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    sealed class Result {
        data class Success(val expenseId: Long) : Result()
        data class Error(val message: String) : Result()
        object DuplicateExpense : Result()
    }

    suspend operator fun invoke(expense: Expense): Result {
        return try {
            if (repository.isDuplicateExpense(expense)) {
                return Result.DuplicateExpense
            }

            if (expense.title.isBlank()) {
                return Result.Error("Title cannot be empty")
            }

            if (expense.amount <= 0) {
                return Result.Error("Amount must be greater than 0")
            }

            if (expense.notes != null && expense.notes.length > 100) {
                return Result.Error("Notes cannot exceed 100 characters")
            }

            val expenseId = repository.insertExpense(expense)
            Result.Success(expenseId)

        } catch (e: Exception) {
            Result.Error(e.message ?: "Failed to add expense")
        }
    }
}