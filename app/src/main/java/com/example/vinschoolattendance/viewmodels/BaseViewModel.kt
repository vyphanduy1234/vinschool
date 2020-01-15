package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinschoolattendance.network.Network
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException

open class BaseViewModel: ViewModel() {

    protected var _internetStatus: MutableLiveData<Int> =
        MutableLiveData()

    protected var _recordStatus: MutableLiveData<Int> =
        MutableLiveData()

    fun getInternetStatus(): LiveData<Int> = _internetStatus

    fun getRecordStatus(): LiveData<Int> = _recordStatus

    /**
     * xử lý lỗi kết nối quá thời gian
     * */
    fun onConnectError(error: Throwable?){
       // if(error.)
        if(error is HttpException){
            if(error.code() == 404){
                _recordStatus.value = Network.NOT_FOUND
            }else if(error.code() == 400){
                _recordStatus.value = Network.BAD_REQUEST
            }else{
                _internetStatus.value = Network.NETWORK_CONNECT_ERROR
            }
        }
    }
}