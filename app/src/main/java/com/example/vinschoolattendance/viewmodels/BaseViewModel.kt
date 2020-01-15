package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinschoolattendance.network.Network

open class BaseViewModel: ViewModel() {

    protected var _internetStatus: MutableLiveData<Int> =
        MutableLiveData()

    fun getInternetStatus(): LiveData<Int> = _internetStatus

    /**
     * xử lý lỗi kết nối quá thời gian
     * */
    fun onConnectError(error: Throwable?){
        _internetStatus.value = Network.NETWORK_CONNECT_ERROR
    }
}