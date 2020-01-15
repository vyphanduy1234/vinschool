package com.example.vinschoolattendance.repositories

import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.network.Header
import io.reactivex.Completable
import io.reactivex.Observable

object StudentRepository {

    /**
     * lấy thông tin thời khóa biểu của học sinh theo ngày
     * @property date: ngày muốn lấy thời khóa biểu
     * @return danh sách thời khóa biểu dạng observable
     * */
      fun  fetchStudentSchedule(sid: Int,date: String): Observable<List<StudentSchedule>> {
        val service: ApiServices = ApiUtils.getApiService()
          val header: MutableMap<String,String> = mutableMapOf()
         return service.getStudentSchedule(Header.header, date)
             .map {
                 it.map {item -> item.toStudentSchedule()}
             }
    }

    /**
     * sinh viên điểm danh theo mã thời khóa biểu
     * @property scheduleId: mã thời khóa biểu
     * */
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