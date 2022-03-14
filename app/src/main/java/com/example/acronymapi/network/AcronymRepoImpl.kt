package com.example.acronymapi.network

import com.example.acronymapi.common.ServiceResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AcronymRepoImpl @Inject constructor(
    private val dispatcher: Dispatchers,
    private val retroObject: AcronymEndpoints
) : AcronymRepo {
    override suspend fun getResults(acc: String): ServiceResult<List<DataModel>?> {
        return withContext(dispatcher.IO){
            val data = retroObject.getResults(acc = acc)
            if(data.isSuccessful){
                ServiceResult.Success(data.body())
            }else{
                ServiceResult.Error(Exception(data.body().toString()))
            }
        }
    }
}
