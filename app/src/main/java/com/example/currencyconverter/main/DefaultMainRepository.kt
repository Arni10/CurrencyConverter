package com.example.currencyconverter.main

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            val isRates = result?.rates
            if (response.isSuccessful && result != null && isRates != null) {
                Resource.Success(result)
            } else {
                if(isRates==null) {
                    Resource.Error("No API key found/ API key is invalid")
                }
                else {
                    Resource.Error(response.message())
                }
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}