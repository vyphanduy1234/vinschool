package com.example.vinschoolattendance.repositories

import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.base.BaseProfile
import com.example.vinschoolattendance.models.pojos.*
import io.reactivex.Completable
import io.reactivex.Observable

object HelperRepository {
    private var service: ApiServices = ApiUtils.getApiService()

    //lay ve danh sach cac mon hoc tu server
    fun fetchSubject(): Observable<MutableList<SubjectResponse>> {
        return service.getAllSubject()
    }

    //lay ve danh sach cac phong hoc tu server
    fun fetchRoom(): Observable<MutableList<RoomResponse>> {
        return service.getAllRoom()
    }

    //lay ve danh sach cac lop tu server
    fun fetchClass(): Observable<MutableList<ClassResponse>> {
        return service.getAllClass()
    }

    //lay ve danh sach cac ki hoc tu server
    fun fetchTerm(): Observable<MutableList<TermResponse>> {
        return service.getAllTerm()
    }

    //lay ve danh sach cac ki hoc tu server
    fun fetchTeacher(): Observable<MutableList<TeacherResponse>> {
        return service.getAllTeacher()
    }

    //lay ve danh sach cac ki hoc tu server
    fun fetchSlot(): Observable<MutableList<SlotResponse>> {
        return service.getAllSlot()
    }

    //lay ve thong tin nguoi dung
    fun fetchStudentInfo(sid: Int): Observable<BaseProfile>{
        return service.getStudentInfo(sid)
    }

    fun fetchTeacherInfo(tid: Int): Observable<BaseProfile>{
        return service.getTeacherInfo(tid)
    }

    fun registerSchedule(scheduleReq: ScheduleRegisterRequest): Completable{
        return service.registerSchedule(scheduleReq)
    }

    fun addNewStudent(studentRequest: StudentRequest): Completable{
        return service.addNewStudent(studentRequest)
    }

}