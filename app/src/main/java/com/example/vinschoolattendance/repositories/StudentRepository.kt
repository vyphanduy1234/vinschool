package com.example.vinschoolattendance.repositories

import android.util.Log
import android.widget.Toast
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.adapters.StudentScheduleAdapter
import com.example.vinschoolattendance.models.pojos.StudentScheduleResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.logging.LogManager

object StudentRepository {

      fun  fetchStudentSchedule(date: String): Observable<List<StudentSchedule>> {
        val service: ApiServices = ApiUtils.getApiService()
         return service.getStudentSchedule(2, date)
             .map {
                 it.map {item -> item.toStudentSchedule()}
             }
    }

     fun  takeAttendance(sid: Int, scheduleId: Int): Completable{
        val service: ApiServices = ApiUtils.getApiService()
        return service.takeAttendanceForStudent(sid, scheduleId)
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