package com.upce.currencyvol2.data

import com.upce.currencyvol2.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {
    @GET("latest.json")
    suspend fun getRates(
        @Query("app_id") apiKey: String = "4b8edd7aa6c94f16a9ef5fef1d19f088",
        @Query("base") base: String = "USD"
    ): Response<CurrencyResponse>
}
