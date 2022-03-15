package com.example.acronymapi.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymapi.common.ServiceResult
import com.example.acronymapi.network.AcronymRepo
import com.example.acronymapi.network.AcronymResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val dispatchers: CoroutineDispatcher,
    private val retroObject: AcronymRepo
) : ViewModel() {

    var _accResults = MutableLiveData<List<AcronymResults>?>()
    val accResults = _accResults

    var goodSearch = MutableLiveData<Boolean>()

    var searchWord = MutableLiveData<String>()

    fun checkLength():Boolean{
        return searchWord.value?.length ?: 0 > 1
    }

    fun getAcronymResults(){
        viewModelScope.launch(dispatchers){
            when(val response = retroObject.getResults(searchWord.value.toString())){
                is ServiceResult.Success->{
                    if(response.data.isNullOrEmpty()){
                        goodSearch.postValue(false)
                    }else {
                        goodSearch.postValue(true)
                        _accResults.postValue(response.data?.get(0)?.lfs)
                    }
                }
                is ServiceResult.Error->{}
                else->{}
            }
        }
    }
}
