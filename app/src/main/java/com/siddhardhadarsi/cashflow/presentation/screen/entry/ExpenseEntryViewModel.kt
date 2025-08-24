package com.siddhardhadarsi.cashflow.presentation.screen.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddhardhadarsi.cashflow.data.repository.ExpenseRepositoryImpl
import com.siddhardhadarsi.cashflow.domain.model.Expense
import com.siddhardhadarsi.cashflow.domain.model.ExpenseCategory
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseEntryViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {
    private val _uiState = kotlinx.coroutines.flow.MutableStateFlow(ExpenseEntryUiState())
    val uiState = _uiState.asStateFlow()

    private val _todayTotal = kotlinx.coroutines.flow.MutableStateFlow(0.0)
    val todayTotal = _todayTotal.asStateFlow()

    init {
        loadTodayTotal()
    }

    private fun loadTodayTotal() {
        viewModelScope.launch {
            val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                .format(java.util.Date())
            _todayTotal.value = repository.getTotalForDate(today)
        }
    }

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title, titleError = null)
    }

    fun updateAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount, amountError = null)
    }

    fun updateCategory(category: ExpenseCategory) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun updateNotes(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun submitExpense() {
        val currentState = _uiState.value

        // Validation
        var hasError = false
        var titleError: String? = null
        var amountError: String? = null

        if (currentState.title.isBlank()) {
            titleError = "Title is required"
            hasError = true
        }

        val amountValue = currentState.amount.toDoubleOrNull()
        if (amountValue == null || amountValue <= 0) {
            amountError = "Enter a valid amount greater than 0"
            hasError = true
        }

        if (hasError) {
            _uiState.value = currentState.copy(
                titleError = titleError,
                amountError = amountError
            )
            return
        }

        _uiState.value = currentState.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val expense = Expense(
                    title = currentState.title.trim(),
                    amount = amountValue!!,
                    category = currentState.selectedCategory,
                    notes = if (currentState.notes.isBlank()) null else currentState.notes.trim()
                )

                repository.insertExpense(expense)

                _uiState.value = ExpenseEntryUiState(showSuccess = true)
                loadTodayTotal()

                // Reset success state after delay
                kotlinx.coroutines.delay(2000)
                _uiState.value = _uiState.value.copy(showSuccess = false)

            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    error = "Failed to save expense: ${e.message}"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}