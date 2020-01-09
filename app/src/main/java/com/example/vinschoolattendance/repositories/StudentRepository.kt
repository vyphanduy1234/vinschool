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
import java.lang.Exception
import java.util.logging.LogManager

class StudentRepository {

     var listStudentSchedule: MutableList<StudentSchedule> = mutableListOf()
     open fun  fetchStudentSchedule(): Observable<List<StudentSchedule>> {
        val service: ApiServices = ApiUtils.getApiService()
         return service.getStudentSchedule(2, "2020-01-08")
             .map {
                 it.map {item -> item.toStudentSchedule()}
             }
    }

//    open fun fakeStudentSchedule(){
//        val studentSchedule1: StudentSchedule = StudentSchedule()
//        val studentSchedule2: StudentSchedule = StudentSchedule()
//        val studentSchedule3: StudentSchedule = StudentSchedule()
//
//        listStudentSchedule.add(studentSchedule1)
//        listStudentSchedule.add(studentSchedule2)
//        listStudentSchedule.add(studentSchedule3)
//    }

}