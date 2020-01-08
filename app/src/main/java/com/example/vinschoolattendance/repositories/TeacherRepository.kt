package com.example.vinschoolattendance.repositories

import android.util.Log
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.example.vinschoolattendance.models.pojos.StudentScheduleResponse
import com.example.vinschoolattendance.models.pojos.TeacherScheduleResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class TeacherRepository {

    lateinit var listTeacherSchedule: MutableList<TeacherSchedule>
    fun getTeacherSchedule() : MutableList<TeacherSchedule>{
        val service: ApiServices = ApiUtils.getApiService()
        val response: Observable<MutableList<TeacherScheduleResponse>> = service.getTeacherSchedule()

        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::onNext ,{error -> Log.d("StudentRepository",error.message)})

        return listTeacherSchedule
    }

    fun onNext(response: MutableList<TeacherScheduleResponse>){
                for(item in response){
            val castItem = item.toTeacherSchedule()
            listTeacherSchedule.add(castItem)
        }
    }
}