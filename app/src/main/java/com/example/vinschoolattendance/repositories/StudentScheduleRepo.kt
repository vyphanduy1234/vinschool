package com.example.vinschoolattendance.repositories

import android.util.Log
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class StudentScheduleRepo {

    open fun getStudentSchedule() {
        val service: ApiServices = ApiUtils.getApiService()
        val call:Call<StudentSchedule>  = service.getStudentSchedule("1")
        call.enqueue(object: Callback<StudentSchedule>{
            override fun onResponse(
                call: Call<StudentSchedule>?,
                response: Response<StudentSchedule>?
            ) {
                Log.d("Retrofit_Vy",response!!.body().toString())
            }

            override fun onFailure(call: Call<StudentSchedule>?, t: Throwable?) {
                Log.d("Retrofit_Vy_error",t!!.message)
            }
        })
    }
}