package com.example.vinschoolattendance.repositories

import android.preference.PreferenceActivity
import android.util.Log
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.example.vinschoolattendance.models.pojos.StudentScheduleResponse
import com.example.vinschoolattendance.models.pojos.TeacherScheduleResponse
import com.example.vinschoolattendance.network.Header
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

object TeacherRepository {

    /**
     * lấy thông tin thời khóa biểu của giáo viên theo ngày
     * @property date: ngày muốn lấy thời khóa biểu
     * @return danh sách thời khóa biểu dạng observable
     * */
    fun fetchTeacherSchedule(sid: Int,date: String): Observable<List<TeacherSchedule>> {
        val service: ApiServices = ApiUtils.getApiService()
        return service.getTeacherSchedule(Header.header,date)
            .map { items -> items.map { item -> item.toTeacherSchedule() } }
    }


}