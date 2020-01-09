package com.appsnipp.creativelogindesigns.api

import com.example.vinschoolattendance.models.pojos.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import io.reactivex.Observable
import retrofit2.http.Query

interface ApiServices {
    @GET("/api/student/schedule/{sid}")
    fun getStudentSchedule(@Path("sid") sid: Int, @Query("date") date: String): Observable<MutableList<StudentScheduleResponse>>

    @GET("/api/teacher/schedule/{tid}")
    fun getTeacherSchedule(@Path("tid") tid: Int, @Query("date") date: String): Observable<MutableList<TeacherScheduleResponse>>

    fun getAllClass(): Observable<MutableList<ClassResponse>>

    fun getAllRoom(): Observable<MutableList<RoomResponse>>

    fun getAllTerm(): Observable<MutableList<TermResponse>>

    fun getAllSubject(): Observable<MutableList<SubjectResponse>>

}