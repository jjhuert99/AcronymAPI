package com.example.acronymapi.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AcronymEndpoints {
    @GET("dictionary.py")
    suspend fun getResults(
        @Query("sf") acc: String
    ) : Response<List<DataModel>>
}
