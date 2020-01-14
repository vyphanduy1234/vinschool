package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.entities.StudentOfClass
import com.example.vinschoolattendance.models.pojos.ClassAttendanceResponse
import com.example.vinschoolattendance.repositories.ClassAttendanceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.vinschoolattendance.network.Network
import java.util.concurrent.TimeUnit

class ClassAttendaceViewModel : BaseViewModel() {

    private var _listClassAttendance: MutableLiveData<MutableList<StudentOfClass>> =
        MutableLiveData()

    fun getListClassAttendance() = _listClassAttendance

    fun loadClassAttend(scheduleId: Int) {
        ClassAttendanceRepository.fetchClassAttendance(scheduleId)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _listClassAttendance.value = it
                    _internetStatus.value = Network.NETWORK_CONNECT_OK
                }, this::onConnectError
            )
    }

    fun editStudentAttend(sid: Int, scheduleId: Int,isPresent: Boolean){
        ApiUtils.getApiService().editAttendanceForStudent(sid,scheduleId,isPresent)
    }

}