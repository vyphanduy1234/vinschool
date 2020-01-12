package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsnipp.creativelogindesigns.model.StudentSchedule

class LoginViewModel: BaseViewModel() {

    private var listStudentSchedule: MutableLiveData<List<StudentSchedule>>? = null

    fun getListStudentSchedule(): LiveData<List<StudentSchedule>>{
        return listStudentSchedule!!
    }
}