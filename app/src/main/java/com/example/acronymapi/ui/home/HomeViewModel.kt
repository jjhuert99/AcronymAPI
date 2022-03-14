package com.example.acronymapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymapi.common.ServiceResult
import com.example.acronymapi.network.AcronymRepo
import com.example.acronymapi.network.AcronymResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val retroObject: AcronymRepo
) : ViewModel() {

    var _accResults = MutableLiveData<List<AcronymResults>?>()
    val accResults = _accResults

    init {
        getAcronymResults()
    }

    private fun getAcronymResults() {
        viewModelScope.launch(dispatchers.IO){
            when(val response = retroObject.getResults()){
                is ServiceResult.Success->{
                    _accResults.postValue(response?.data?.get(0)?.lfs)
                }
                is ServiceResult.Error->{

                }
                else->{}
            }
        }
    }
}
