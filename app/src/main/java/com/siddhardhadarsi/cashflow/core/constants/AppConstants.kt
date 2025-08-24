package com.siddhardhadarsi.cashflow.core.constants

object AppConstants {
    // App Info
    const val APP_NAME = "CashFlow Pro"
    const val APP_VERSION = "1.0.0"
    const val APP_TAGLINE = "Smart Expense Tracking for Smart Business Owners"

    // Database
    const val DATABASE_NAME = "cashflow_pro_database"
    const val DATABASE_VERSION = 1

    // Preferences
    const val PREFERENCES_NAME = "cashflow_pro_preferences"
    const val THEME_KEY = "theme_preference"
    const val CURRENCY_KEY = "currency_preference"
    const val FIRST_LAUNCH_KEY = "first_launch"

    // Validation
    const val MAX_TITLE_LENGTH = 50
    const val MAX_NOTES_LENGTH = 100
    const val MIN_AMOUNT = 0.01
    const val MAX_AMOUNT = 999999.99

    // UI
    const val ANIMATION_DURATION = 300
    const val DEBOUNCE_DELAY = 500L
    const val SPLASH_DELAY = 2000L

    // Report
    const val DEFAULT_REPORT_DAYS = 7
    const val MAX_REPORT_DAYS = 365

    // Export
    const val EXPORT_DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss"
    const val REPORT_FILE_PREFIX = "CashFlowPro_Report_"

    // Categories
    val DEFAULT_CATEGORIES = listOf("Staff", "Travel", "Food", "Utility")

    // Currency
    const val DEFAULT_CURRENCY = "INR"
    const val CURRENCY_SYMBOL = "₹"

    // Date Formats
    const val DATE_FORMAT_DISPLAY = "MMM dd, yyyy"
    const val DATE_FORMAT_API = "yyyy-MM-dd"
    const val TIME_FORMAT_DISPLAY = "HH:mm"
    const val DATETIME_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss"

    // Network (for future use)
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L

    // Error Messages
    const val ERROR_GENERIC = "Something went wrong. Please try again."
    const val ERROR_NETWORK = "Please check your internet connection."
    const val ERROR_DATABASE = "Database error occurred."
    const val ERROR_VALIDATION = "Please check your input and try again."

    // Success Messages
    const val SUCCESS_EXPENSE_ADDED = "Expense added successfully!"
    const val SUCCESS_EXPENSE_DELETED = "Expense deleted successfully!"
    const val SUCCESS_REPORT_EXPORTED = "Report exported successfully!"
    const val SUCCESS_DATA_SYNCED = "Data synced successfully!"
}