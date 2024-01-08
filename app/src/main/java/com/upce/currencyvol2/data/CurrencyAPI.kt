package com.upce.currencyvol2.data


import com.upce.currencyvol2.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
//import com.upce.currencyvol2.BuildConfig


interface CurrencyAPI {

    @GET("/latest")
    suspend fun getRates(
        @Query("access_key") apiKey: String = "d9838b839746003cf05ef82af3585c0c"
    ): Response<CurrencyResponse>

}


