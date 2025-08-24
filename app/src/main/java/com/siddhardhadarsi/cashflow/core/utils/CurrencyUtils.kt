package com.siddhardhadarsi.cashflow.core.utils

object CurrencyUtils {
    fun formatAmount(amount: Double): String {
        return "₹${String.format("%.2f", amount)}"
    }

    fun parseAmount(amountString: String): Double? {
        return try {
            amountString.replace("₹", "").replace(",", "").toDoubleOrNull()
        } catch (e: Exception) {
            null
        }
    }
}