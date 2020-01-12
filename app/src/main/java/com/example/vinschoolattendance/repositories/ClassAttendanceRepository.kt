package com.example.vinschoolattendance.repositories

import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.entities.StudentOfClass
import io.reactivex.Observable

object ClassAttendanceRepository {

    fun fetchClassAttendance(scheduleId: Int): Observable<MutableList<StudentOfClass>> {
        return ApiUtils.getApiService().getAttendaceOfClass(scheduleId)
            .map {
                it.map {
                    it.toStudentOfClass(scheduleId)
                }.toMutableList()
            }
    }
}