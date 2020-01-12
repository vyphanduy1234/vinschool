package com.example.vinschoolattendance.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.StudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentViewModel : BaseViewModel() {

    companion object{
         val TAKE_SUCCESS: Int = 1
         val TAKE_FAIL: Int = 0
    }
    private var _listStudentSchedule: MutableLiveData<MutableList<StudentSchedule>> =
        MutableLiveData()

    private var _studentTakeAttendStatus: MutableLiveData<Int> =
        MutableLiveData()

    fun getListStudentSchedule() = _listStudentSchedule

    fun getStudentTakeAttendStatus() = _studentTakeAttendStatus

    fun loadStudentSchedule() {
         StudentRepository.fetchStudentSchedule()
             .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe({
                 _listStudentSchedule.value = it.toMutableList()
                 _internetStatus.value = Network.NETWORK_CONNECT_OK
             },this::onConnectError)
    }

    fun takeAttendance(sid: Int, scheduleId: Int){
        StudentRepository.takeAttendance(sid,scheduleId)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _studentTakeAttendStatus.value = TAKE_SUCCESS
            },{
                error -> this.onConnectError(error )
                _studentTakeAttendStatus.value = TAKE_FAIL
            })
    }
}
