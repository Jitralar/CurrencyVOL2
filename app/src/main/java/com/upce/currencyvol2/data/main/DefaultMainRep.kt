package com.upce.currencyvol2.data.main

import com.upce.currencyvol2.data.CurrencyAPI
import com.upce.currencyvol2.data.models.CurrencyResponse
import com.upce.currencyvol2.data.util.Resource
import javax.inject.Inject

class DefaultMainRep @Inject constructor(
    private val api: CurrencyAPI
) : MainRepository {
    suspend fun getRates(): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getRates(appId: String, base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(appId, base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
