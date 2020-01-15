package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinschoolattendance.models.base.BaseProfile
import com.example.vinschoolattendance.models.pojos.*
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.HelperRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HelperViewModel : BaseViewModel() {

    companion object {
        val REGISTER_SUCCESS: Int = 1
        val REGISTER_FAIL: Int = 0
        val ADD_SUCCESS: Int = 1
        val ADD_FAIL: Int = 0
    }

    private var _listRoom: MutableLiveData<MutableList<RoomResponse>> = MutableLiveData()
    private var _listClass: MutableLiveData<MutableList<ClassResponse>> = MutableLiveData()
    private var _listTerm: MutableLiveData<MutableList<TermResponse>> = MutableLiveData()
    private var _listTeacher: MutableLiveData<MutableList<TeacherResponse>> = MutableLiveData()
    private var _listSubject: MutableLiveData<MutableList<SubjectResponse>> = MutableLiveData()
    private var _listSlot: MutableLiveData<MutableList<SlotResponse>> = MutableLiveData()
    private var _teacherProfile: MutableLiveData<BaseProfile> = MutableLiveData()
    private var _studentProfile: MutableLiveData<BaseProfile> = MutableLiveData()

    private var _registerScheduleStatus: MutableLiveData<Int> = MutableLiveData()
    private var _addStudentStatus: MutableLiveData<Int> = MutableLiveData()


    fun getRoom() = _listRoom

    fun getClass() = _listClass

    fun getSubject() = _listSubject

    fun getTerm() = _listTerm

    fun getTeacher() = _listTeacher

    fun getSlot() = _listSlot

    fun getTeacherProfile() = _teacherProfile

    fun getStudentProfile() = _studentProfile

    fun getRegisterScheduleStatus() = _registerScheduleStatus

    fun getAddStudentStatus() = _addStudentStatus

    fun loadClass() {
        //load class
        HelperRepository.fetchClass()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listClass.value = it
            }, this::onConnectError)
    }

    /**
     * lấy về danh sách các lớp học , phòng học, kì học, môn học,thời gian học
     */
    fun loadResource() {
        //load subject
        HelperRepository.fetchSubject()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listSubject.value = it
            }, this::onConnectError)

        //load class
        HelperRepository.fetchClass()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listClass.value = it
            }, this::onConnectError)

        //load term
        HelperRepository.fetchTerm()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listTerm.value = it
            }, this::onConnectError)

        //load room
        HelperRepository.fetchRoom()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listRoom.value = it
            }, this::onConnectError)

        HelperRepository.fetchTeacher()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listTeacher.value = it
            }, this::onConnectError)

        HelperRepository.fetchSlot()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listSlot.value = it
            }, this::onConnectError)
    }

    fun loadStudentProfile(id: Int) {
        HelperRepository.fetchStudentInfo(id)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _studentProfile.value = it
            }, this::onConnectError)
    }

    /**
     * lấy về thông tin của giáo viên
     * */
    fun loadTeacherProfile(id: Int) {
        HelperRepository.fetchTeacherInfo(id)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _teacherProfile.value = it
            }, this::onConnectError)
    }

    /**
     * đăng kí tiết học mới cho 1 lớp học
     * @property scheduleReq: thông tin tiết học bao gồm :
     * */
    fun registerSchedule(scheduleReq: ScheduleRegisterRequest) {
        HelperRepository.registerSchedule(scheduleReq)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _registerScheduleStatus.value = REGISTER_SUCCESS
            }, {
                this.onConnectError(it)
                _registerScheduleStatus.value = REGISTER_FAIL
            })
    }

    /**
     * thêm mới một học sinh  cho 1 lớp
     * @property studentRequest: thông tin một học sinh bao gồm:
     * */
    fun addNewStudent(studentRequest: StudentRequest) {
        HelperRepository.addNewStudent(studentRequest)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _addStudentStatus.value = ADD_SUCCESS
            }, {
                this.onConnectError(it)
                _addStudentStatus.value = ADD_FAIL
            })

    }
}