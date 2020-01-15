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

    companion object{
        val UPDATE_FAIL = 0
        val UPDATE_SUCCESS = 1
    }

    private var _listClassAttendance: MutableLiveData<MutableList<StudentOfClass>> =
        MutableLiveData()

    private var _updateAttendOfStudentStatus: MutableLiveData<Int> = MutableLiveData()
    fun getListClassAttendance() = _listClassAttendance

    /**
     * lấy ra danh sách các sinh viên của 1 lớp theo mã thời khóa biểu
     * @property scheduleId: mã thời khóa biểu
     * @retun danh sách các sinh viên của 1 lớp theo mã thời khóa biểu
     * */
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

    /**
     * lấy ra danh sách các sinh viên của 1 lớp theo mã thời khóa biểu
     * @property scheduleId: mã thời khóa biểu
     * @property isPresent: trạng thái điểm danh
     * */
    fun editStudentAttend(sid: Int, scheduleId: Int,isPresent: Boolean){
        ApiUtils.getApiService().editAttendanceForStudent(sid,scheduleId,isPresent)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _updateAttendOfStudentStatus.value = UPDATE_SUCCESS
            },{
                this.onConnectError(it)
                _updateAttendOfStudentStatus.value = UPDATE_FAIL
            })
    }

}