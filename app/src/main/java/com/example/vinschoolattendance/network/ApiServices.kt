package com.appsnipp.creativelogindesigns.api

import com.example.vinschoolattendance.models.base.BaseProfile
import com.example.vinschoolattendance.models.pojos.*
import io.reactivex.Completable
import retrofit2.Call
import java.util.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServices {
    @GET("/api/student/schedule/{sid}")
    fun getStudentSchedule(@Path("sid") sid: Int, @Query("date") date: String): Observable<MutableList<StudentScheduleResponse>>

    @POST("/api/teacher")
    @Headers("Content-Type: application/json")
    fun login(@Body scheduleReq: ScheduleRegisterRequest): Completable

    @GET("/api/teacher/schedule/{tid}")
    fun getTeacherSchedule(@Path("tid") tid: Int, @Query("date") date: String): Observable<MutableList<TeacherScheduleResponse>>

    @GET("/api/teacher/attendance/{scheduleId}")
    fun getAttendaceOfClass(@Path("scheduleId") scheduleId: Int): Observable<MutableList<ClassAttendanceResponse>>

    @GET("/api/student/info/{uid}")
    fun getStudentInfo(@Path("uid") uid: Int): Observable<BaseProfile>

    @GET("/api/teacher/info/{uid}")
    fun getTeacherInfo(@Path("uid") uid: Int): Observable<BaseProfile>

    @PUT("/api/student/attendance/{id}")
    fun takeAttendanceForStudent(@Path("id") id: Int,@Query("schedule_id") scheduleId: Int): Completable

    @PUT("/api/student/attendance/{id}")
    fun editAttendanceForStudent(@Path("id") id: Int,@Query("schedule_id") scheduleId: Int): Completable

    @POST("/api/teacher")
    @Headers("Content-Type: application/json")
    fun registerSchedule(@Body scheduleReq: ScheduleRegisterRequest): Completable

    @GET("/api/teacher/getAllClassInfo")
    fun getAllClass(): Observable<MutableList<ClassResponse>>

    @GET("/api/teacher/getAllRoomInfo")
    fun getAllRoom(): Observable<MutableList<RoomResponse>>

    @GET("/api/teacher/getAllTermInfo")
    fun getAllTerm(): Observable<MutableList<TermResponse>>

    @GET("/api/teacher/getAllSubjectInfo")
    fun getAllSubject(): Observable<MutableList<SubjectResponse>>

    @GET("/api/teacher/getAllTeacherInfo")
    fun getAllTeacher(): Observable<MutableList<TeacherResponse>>

    @GET("/api/teacher/getAllCategoryInfo")
    fun getAllSlot(): Observable<MutableList<SlotResponse>>

}