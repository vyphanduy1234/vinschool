package com.example.vinschoolattendance.repositories

import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.base.BaseProfile
import com.example.vinschoolattendance.models.pojos.*
import io.reactivex.Completable
import io.reactivex.Observable

object HelperRepository {
    private var service: ApiServices = ApiUtils.getApiService()

    /**
     * lấy về danh sách học sinh của một lớp học theo mã lớp học từ server
     * @return một đối tượng observable của 1 list các môn học
     * */
    fun fetchSubject(): Observable<MutableList<SubjectResponse>> {
        return service.getAllSubject()
    }

    /**
     * lấy về danh sách các phòng học từ server
     * @return một đối tượng observable của 1 list các phòng học
     * */
    fun fetchRoom(): Observable<MutableList<RoomResponse>> {
        return service.getAllRoom()
    }

    /**
     * lấy về danh sách các lớp học từ server
     * @return một đối tượng observable của 1 list các lớp học
     * */
    fun fetchClass(): Observable<MutableList<ClassResponse>> {
        return service.getAllClass()
    }

    /**
     * lấy về danh sách các kì học từ server
     * @return một đối tượng observable của 1 list các kì học
     * */
    fun fetchTerm(): Observable<MutableList<TermResponse>> {
        return service.getAllTerm()
    }

    /**
     * lấy về danh sách các giáo viên từ server
     * @return một đối tượng observable của 1 list các giáo viên
     * */
    fun fetchTeacher(): Observable<MutableList<TeacherResponse>> {
        return service.getAllTeacher()
    }

    /**
     * lấy về danh sách các khoảng thời gian học từ server
     * @return một đối tượng observable của 1 list các khoảng thời gian học
     * */
    fun fetchSlot(): Observable<MutableList<SlotResponse>> {
        return service.getAllSlot()
    }

    /**
     * lấy về thông tin của học sinh từ server theo token
     * @return một đối tượng observable của thông tin học sinh
     * */
    fun fetchStudentInfo(sid: Int): Observable<BaseProfile>{
        return service.getStudentInfo(sid)
    }

    /**
     * lấy về thông tin của giáo viên từ server theo token
     * @return một đối tượng observable của thông tin giáo viên
     * */
    fun fetchTeacherInfo(tid: Int): Observable<BaseProfile>{
        return service.getTeacherInfo(tid)
    }

    /**
     * thêm mới một tiết học lên db server
     * */
    fun registerSchedule(scheduleReq: ScheduleRegisterRequest): Completable{
        return service.registerSchedule(scheduleReq)
    }

    /**
     * thêm mới một hoc sinh lên db server
     * */
    fun addNewStudent(studentRequest: StudentRequest): Completable{
        return service.addNewStudent(studentRequest)
    }

}