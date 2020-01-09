package com.example.vinschoolattendance.repositories

import android.util.Log
import androidx.room.Room
import com.appsnipp.creativelogindesigns.api.ApiServices
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.example.vinschoolattendance.models.pojos.ClassResponse
import com.example.vinschoolattendance.models.pojos.RoomResponse
import com.example.vinschoolattendance.models.pojos.SubjectResponse
import com.example.vinschoolattendance.models.pojos.TermResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.security.auth.Subject

class HelperRepository {
    lateinit var listSubject: MutableList<SubjectResponse>
    lateinit var listRoom: MutableList<RoomResponse>
    lateinit var listClass: MutableList<ClassResponse>
    lateinit var listTerm: MutableList<TermResponse>
    var service: ApiServices = ApiUtils.getApiService()

    //lay ve danh sach cac mon hoc tu server
    fun fetchSubject(): Observable<MutableList<SubjectResponse>> {
        return service.getAllSubject()
    }

    //lay ve danh sach cac phong hoc tu server
    fun fetchRoom(): Observable<MutableList<RoomResponse>> {
        return service.getAllRoom()
    }

    //lay ve danh sach cac lop tu server
    fun fetchClass(): Observable<MutableList<ClassResponse>> {
        return service.getAllClass()
    }

    //lay ve danh sach cac ki hoc tu server
    fun fetchTerm(): Observable<MutableList<TermResponse>> {
        return service.getAllTerm()
    }

}