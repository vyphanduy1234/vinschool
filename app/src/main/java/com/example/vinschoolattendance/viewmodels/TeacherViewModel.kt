package com.example.vinschoolattendance.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.TeacherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TeacherViewModel : BaseViewModel() {
    private var _listTeacherSchedule: MutableLiveData<MutableList<TeacherSchedule>> =
        MutableLiveData()

    fun getTeacherSchedule() = _listTeacherSchedule

    /**
     * lấy về thời khóa biểu cho giáo viên
     * @property date: ngày cần lấy
     * */
    fun loadTeacherSchedule(sid: Int,date: String) {
        TeacherRepository.fetchTeacherSchedule(sid,date)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ schedule ->
                _listTeacherSchedule.value = schedule.toMutableList()
                _internetStatus.value = Network.NETWORK_CONNECT_OK
            },this::onConnectError
            )
    }
}