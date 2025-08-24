package com.siddhardhadarsi.cashflow.domain.model

enum class ExpenseCategory(val displayName: String, val color: androidx.compose.ui.graphics.Color) {
    STAFF("Staff", androidx.compose.ui.graphics.Color(0xFF2196F3)),
    TRAVEL("Travel", androidx.compose.ui.graphics.Color(0xFF4CAF50)),
    FOOD("Food", androidx.compose.ui.graphics.Color(0xFFFF9800)),
    UTILITY("Utility", androidx.compose.ui.graphics.Color(0xFF9C27B0))
}