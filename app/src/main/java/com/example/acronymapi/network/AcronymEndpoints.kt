package com.example.acronymapi.network

import retrofit2.Response
import retrofit2.http.GET

interface AcronymEndpoints {
    @GET("dictionary.py?sf=HOA")
    suspend fun getResults() : Response<List<DataModel>>
}
