package com.appsnipp.creativelogindesigns.api

import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.appsnipp.creativelogindesigns.model.User
import com.example.vinschoolattendance.models.pojos.StudentSchedulePojo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("/api/student/schedule/{sid}")
    fun getStudentSchedule(@Path("sid") sid: String): Call<StudentSchedulePojo>
}