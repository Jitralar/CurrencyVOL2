package com.upce.currencyvol2

import android.app.Application

@CurrencyApplication.HiltAndroidApp
class CurrencyApplication : Application() {
    annotation class HiltAndroidApp
}