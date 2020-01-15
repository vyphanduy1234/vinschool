package com.example.vinschoolattendance.repositories

import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.entities.StudentOfClass
import io.reactivex.Observable

object ClassAttendanceRepository {

    /**
     * lấy về danh sách học sinh của một lớp học theo mã lớp học
     * @property scheduleId : mã thời khóa biểu
     * @return một đối tượng observable của 1 list các học sinh của 1 lớp hoc
     * */
    fun fetchClassAttendance(scheduleId: Int): Observable<MutableList<StudentOfClass>> {
        return ApiUtils.getApiService().getAttendanceOfClass(scheduleId)
            .map {
                it.map {
                    it.toStudentOfClass(scheduleId)
                }.toMutableList()
            }
    }
}