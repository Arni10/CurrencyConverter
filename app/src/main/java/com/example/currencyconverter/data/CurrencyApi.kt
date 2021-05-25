package com.example.currencyconverter.data

import com.example.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest?access_key=6006a06097db7414dc8fbc44338b0f33")
    //@GET("/latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<CurrencyResponse>
}