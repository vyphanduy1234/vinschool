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
    fun getSubject(): MutableList<SubjectResponse> {
        var response: Observable<MutableList<SubjectResponse>> = service.getAllSubject()
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(this::onSubjectNext, { error -> Log.d("", error.message) })

        return listSubject
    }

    //lay ve danh sach cac phong hoc tu server
    fun getRoom(): MutableList<RoomResponse> {
        var response: Observable<MutableList<RoomResponse>> = service.getAllRoom()
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(this::onRoomNext, { error -> Log.d("", error.message) })

        return listRoom
    }

    //lay ve danh sach cac lop tu server
    fun getClass(): MutableList<ClassResponse> {
        var response: Observable<MutableList<ClassResponse>> = service.getAllClass()
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(this::onClassNext, { error -> Log.d("", error.message) })

        return listClass
    }

    //lay ve danh sach cac ki hoc tu server
    fun getTerm(): MutableList<TermResponse> {
        var response: Observable<MutableList<TermResponse>> = service.getAllTerm()
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(this::onTermNext, { error -> Log.d("", error.message) })

        return listTerm
    }

    fun onSubjectNext(response: MutableList<SubjectResponse>) {
        this.listSubject = response
    }

    fun onRoomNext(response: MutableList<RoomResponse>) {
        this.listRoom = response
    }

    fun onTermNext(response: MutableList<TermResponse>) {
        this.listTerm = response
    }

    fun onClassNext(response: MutableList<ClassResponse>) {
        this.listClass = response
    }
}