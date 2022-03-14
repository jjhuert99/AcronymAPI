package com.example.acronymapi.network

import com.example.acronymapi.common.ServiceResult

interface AcronymRepo {
    suspend fun getResults() : ServiceResult<List<DataModel>?>
}
