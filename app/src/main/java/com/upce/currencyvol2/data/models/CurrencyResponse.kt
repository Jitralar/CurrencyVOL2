package com.upce.currencyvol2.data.models

data class CurrencyResponse(
    val base: String,
    val rates: Map<String, Double>
)

