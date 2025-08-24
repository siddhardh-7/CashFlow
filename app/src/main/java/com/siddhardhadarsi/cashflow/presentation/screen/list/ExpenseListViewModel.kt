package com.siddhardhadarsi.cashflow.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddhardhadarsi.cashflow.domain.model.GroupBy
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {
    private val _uiState = kotlinx.coroutines.flow.MutableStateFlow(ExpenseListUiState())
    val uiState = _uiState.asStateFlow()

    private val _selectedDate = kotlinx.coroutines.flow.MutableStateFlow(getCurrentDate())
    val selectedDate = _selectedDate.asStateFlow()

    private val _groupBy = kotlinx.coroutines.flow.MutableStateFlow(GroupBy.TIME)
    val groupBy = _groupBy.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.getExpensesByDate(_selectedDate.value).collect { expenses ->
                val total = expenses.sumOf { it.amount }
                _uiState.value = _uiState.value.copy(
                    expenses = expenses,
                    totalAmount = total,
                    totalCount = expenses.size,
                    isLoading = false
                )
            }
        }
    }

    fun selectDate(date: String) {
        _selectedDate.value = date
        loadExpenses()
    }

    fun toggleGroupBy() {
        _groupBy.value = if (_groupBy.value == GroupBy.TIME) GroupBy.CATEGORY else GroupBy.TIME
    }

    private fun getCurrentDate(): String {
        return java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            .format(java.util.Date())
    }
}