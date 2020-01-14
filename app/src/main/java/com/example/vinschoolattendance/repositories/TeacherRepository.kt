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

object TeacherRepository {

    fun fetchTeacherSchedule(sid: Int,date: String): Observable<List<TeacherSchedule>> {
        val service: ApiServices = ApiUtils.getApiService()
        return service.getTeacherSchedule(sid,date)
            .map { items -> items.map { item -> item.toTeacherSchedule() } }
    }


}