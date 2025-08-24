package com.siddhardhadarsi.cashflow.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CashFlowProApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}