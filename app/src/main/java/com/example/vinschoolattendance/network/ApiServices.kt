package com.appsnipp.creativelogindesigns.api

import com.example.vinschoolattendance.models.pojos.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import io.reactivex.Observable
interface ApiServices {
    @GET("/api/student/schedule/{sid}")
    fun getStudentSchedule(@Path("sid") sid: String): Observable<MutableList<StudentScheduleResponse>>

    fun getTeacherSchedule(): Observable<MutableList<TeacherScheduleResponse>>

    fun getAllClass(): Observable<MutableList<ClassResponse>>

    fun getAllRoom(): Observable<MutableList<RoomResponse>>

    fun getAllTerm(): Observable<MutableList<TermResponse>>

    fun getAllSubject(): Observable<MutableList<SubjectResponse>>

}