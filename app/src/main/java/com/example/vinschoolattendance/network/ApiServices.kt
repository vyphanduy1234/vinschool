package com.appsnipp.creativelogindesigns.api

import com.example.vinschoolattendance.models.base.BaseProfile
import com.example.vinschoolattendance.models.pojos.*
import com.example.vinschoolattendance.utils.UserAuthen
import io.reactivex.Completable
import retrofit2.Call
import java.util.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServices {
    /**
    * lấy thông tin thời khóa biểu của sinh viên theo ngày
    * @property date: ngày muốn lấy thời khóa biểu
    * @property header: lưu token của sinh
    * */
    @GET("/api/student/schedule/")
    fun getStudentSchedule(@HeaderMap headers: Map<String, String> , @Query("date") date: String): Observable<MutableList<StudentScheduleResponse>>

    /**
    * lấy thông tin thời khóa biểu của sinh viên theo ngày
    * @property loginRequest: lưu tài khoản mật khẩu
    * */
    @POST("/api/login")
    @Headers("Content-Type: application/json")
    fun login(@Body loginRequest: LoginRequest): Observable<UserResponse>

    /**
    * lấy thông tin thời khóa biểu của giáo viên theo ngày
    * @property date: ngày muốn lấy thời khóa biểu
    * @property header: lưu token của giáo viên
    * */
    //add header
    @GET("/api/teacher/schedule/{tid}")
    fun getTeacherSchedule(@Path("tid") tid: Int, @Query("date") date: String): Observable<MutableList<TeacherScheduleResponse>>


    /**
    * lấy thông tin điểm danh của 1 lớp theo thời khóa biểu
    * @property scheduleId: mã thời khóa biểu
    * */
    @GET("/api/teacher/attendance/{scheduleId}")
    fun getAttendanceOfClass(@Path("scheduleId") scheduleId: Int): Observable<MutableList<ClassAttendanceResponse>>

    /**
    * lấy thông tin của sinh viên
    * */
    //add header
    @GET("/api/student/info/{uid}")
    fun getStudentInfo(@Path("uid") uid: Int): Observable<BaseProfile>

    /**
    * lấy thông tin của giáo viên
    * */
    //add header
    @GET("/api/teacher/info/{uid}")
    fun getTeacherInfo(@Path("uid") uid: Int): Observable<BaseProfile>

    /**
    * sinh viên điểm danh theo mã thời khóa biểu
    * @property scheduleId: mã thời khóa biểu
    * */
    //add header
    @PUT("/api/student/attendance/{id}")
    fun takeAttendanceForStudent(@Path("id") id: Int,@Query("schedule_id") scheduleId: Int): Completable

    /**
    * giáo viên chỉnh sửa điểm danh cho sinh viên
    * @property scheduleId: mã thời khóa biểu
    * @property isPresent: true - điểm danh có, false - điểm danh không
    * */
    //add header
    @PUT("/api/teacher/take-attendance/{id}")
    fun editAttendanceForStudent(@Path("id") id: Int
                                 ,@Query("scheduleId") scheduleId: Int,
                                 @Query("isPresent") isPresent: Boolean): Completable

    /**
    * giáo viên đăng kí 1 tiết học cho 1 lớp
    * @property scheduleReq: thông tin 1 tiết học gồm có :
    * */
    @POST("/api/teacher")
    @Headers("Content-Type: application/json")
    fun registerSchedule(@Body scheduleReq: ScheduleRegisterRequest): Completable

    /**
    * giáo viên thêm mới một sinh viên
    * @property studentRequest: thông tin một sinh viên gồm có:
    * */
    @POST("/api/teacher/createUser")
    @Headers("Content-Type: application/json")
    fun addNewStudent(@Body studentRequest: StudentRequest): Completable

    /**
    * lấy ra thông tin các lớp học
    * */
    @GET("/api/teacher/getAllClassInfo")
    fun getAllClass(): Observable<MutableList<ClassResponse>>

    /**
    * lấy ra thông tin các lớp học
    * */
    @GET("/api/teacher/getAllRoomInfo")
    fun getAllRoom(): Observable<MutableList<RoomResponse>>

    /**
    * lấy ra thông tin các kì học
    * */
    @GET("/api/teacher/getAllTermInfo")
    fun getAllTerm(): Observable<MutableList<TermResponse>>

    /**
    * lấy ra thông tin các môn học
    * */
    @GET("/api/teacher/getAllSubjectInfo")
    fun getAllSubject(): Observable<MutableList<SubjectResponse>>

    /**
    * lấy ra thông tin các giáo viên
    * */
    @GET("/api/teacher/getAllTeacherInfo")
    fun getAllTeacher(): Observable<MutableList<TeacherResponse>>

    /**
    * lấy ra thông tin các thời gian học
    * */
    @GET("/api/teacher/getAllCategoryInfo")
    fun getAllSlot(): Observable<MutableList<SlotResponse>>

}