package com.siddhardhadarsi.cashflow.data.mapper

import com.siddhardhadarsi.cashflow.data.local.database.ExpenseEntity
import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.model.ExpenseCategory
import javax.inject.Inject
import javax.inject.Singleton

fun ExpenseEntity.toExpense() = Expense(
    id = id,
    title = title,
    amount = amount,
    category = ExpenseCategory.valueOf(category),
    notes = notes,
    receiptImagePath = receiptImagePath,
    timestamp = timestamp,
    date = date
)

fun Expense.toEntity() = ExpenseEntity(
    id = id,
    title = title,
    amount = amount,
    category = category.name,
    notes = notes,
    receiptImagePath = receiptImagePath,
    timestamp = timestamp,
    date = date
)

@Singleton
class ExpenseMapper @Inject constructor() {

    fun entityToDomain(entity: ExpenseEntity): Expense {
        return entity.toExpense()
    }

    fun domainToEntity(domain: Expense): ExpenseEntity {
        return domain.toEntity()
    }
}