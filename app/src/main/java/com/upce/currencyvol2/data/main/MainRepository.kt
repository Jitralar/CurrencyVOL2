package com.upce.currencyvol2.data.main

import com.upce.currencyvol2.data.models.CurrencyResponse
import com.upce.currencyvol2.data.util.Resource

interface MainRepository {
    suspend fun getRates(appId: String, base: String): Resource<CurrencyResponse>
}
