package com.example.vinschoolattendance.repositories

import android.util.Log
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.adapters.StudentScheduleAdapter
import com.example.vinschoolattendance.models.pojos.StudentSchedulePojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class StudentScheduleRepo {

    open fun fetchStudentSchedule(adapter : StudentScheduleAdapter) : MutableList<StudentSchedule> {
        val service: ApiServices = ApiUtils.getApiService()
        var studentScheduleList : MutableList<StudentSchedule> = mutableListOf()
        val call: Call<StudentSchedulePojo> = service.getStudentSchedule("1")
        call.enqueue(object : Callback<StudentSchedulePojo> {
            override fun onResponse(
                call: Call<StudentSchedulePojo>?,
                response: Response<StudentSchedulePojo>?
            ) {
                response?.run {
                    val scPojo = response.body()
                    val sc = scPojo.toStudentSchedule()
                    adapter.addSchedule(sc!!)
                }
            }

            override fun onFailure(call: Call<StudentSchedulePojo>?, t: Throwable?) {
                Log.d("Retrofit_Vy_error", t!!.message)
            }
        })
        return studentScheduleList
    }
}