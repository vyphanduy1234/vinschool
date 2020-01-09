package com.example.vinschoolattendance.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.StudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentViewModel : ViewModel() {
    private var _listStudentSchedule: MutableLiveData<MutableList<StudentSchedule>> =
        MutableLiveData()

    private var _internetStatus: MutableLiveData<Int> =
        MutableLiveData(Network.NETWORK_CONNECT_OK)


    fun getListStudentSchedule() = _listStudentSchedule

    fun getInternetStatus() = _internetStatus

    fun loadStudentSchedule() {
        val studentRepo = StudentRepository()
         studentRepo.fetchStudentSchedule()
             .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe({
                 _listStudentSchedule.value = it.toMutableList()
                 _internetStatus.value = Network.NETWORK_CONNECT_OK
             }, { _internetStatus.value = Network.NETWORK_CONNECT_ERROR})
    }
}
