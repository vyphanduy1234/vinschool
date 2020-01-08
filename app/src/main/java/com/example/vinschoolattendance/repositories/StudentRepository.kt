package com.example.vinschoolattendance.repositories

import android.util.Log
import android.widget.Toast
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.adapters.StudentScheduleAdapter
import com.example.vinschoolattendance.models.pojos.StudentScheduleResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRepository {

    lateinit var listStudentSchedule: MutableList<StudentSchedule>
    open fun getStudentSchedule(adapter : StudentScheduleAdapter) : MutableList<StudentSchedule> {
        val service: ApiServices = ApiUtils.getApiService()

        val response: Observable<MutableList<StudentScheduleResponse>> = service.getStudentSchedule("1")
        response!!.observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(this::onNext ,{error -> Log.d("StudentRepository",error.message)})

        return listStudentSchedule
    }

    fun onNext(response: MutableList<StudentScheduleResponse>) {
        for(item in response){
            val castItem = item.toStudentSchedule()
            listStudentSchedule.add(castItem)
        }
    }
}