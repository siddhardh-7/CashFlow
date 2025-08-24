package com.siddhardhadarsi.cashflow.presentation.screen.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddhardhadarsi.cashflow.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseReportViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {
    private val _uiState = kotlinx.coroutines.flow.MutableStateFlow(ExpenseReportUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadReport()
    }

    private fun loadReport() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val report = repository.generateReport(7)
                _uiState.value = _uiState.value.copy(
                    report = report,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Failed to generate report: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun exportReport() {
        _uiState.value = _uiState.value.copy(showExportSuccess = true)

        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)
            _uiState.value = _uiState.value.copy(showExportSuccess = false)
        }
    }

    fun refreshReport() {
        loadReport()
    }
}