package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinschoolattendance.models.pojos.ClassResponse
import com.example.vinschoolattendance.models.pojos.RoomResponse
import com.example.vinschoolattendance.models.pojos.SubjectResponse
import com.example.vinschoolattendance.models.pojos.TermResponse
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.HelperRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HelperViewModel : ViewModel() {
    lateinit private var listRoom: MutableLiveData<MutableList<RoomResponse>>
    lateinit private var listClass: MutableLiveData<MutableList<ClassResponse>>
    lateinit private var listTerm: MutableLiveData<MutableList<TermResponse>>
    lateinit private var listSubject: MutableLiveData<MutableList<SubjectResponse>>


    fun getRoom(): LiveData<MutableList<RoomResponse>> {
        return listRoom
    }

    fun getClass(): LiveData<MutableList<ClassResponse>> {
        return listClass
    }

    fun getSubject(): LiveData<MutableList<SubjectResponse>> {
        return listSubject
    }

    fun getterm(): LiveData<MutableList<TermResponse>> {
        return listTerm
    }

    fun loadResource() {
        val helperRepo = HelperRepository()
        //load subject
        helperRepo.fetchSubject()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                listSubject.value = it
            }, { error("error load subject") })

        //load class
        helperRepo.fetchClass()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                listClass.value = it
            }, { error("error load class") })

        //load term
        helperRepo.fetchTerm()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                listTerm.value = it
            }, { error("error load term") })

        //load room
        helperRepo.fetchRoom()
            .timeout(Network.NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                listRoom.value = it
            }, { error("error load room") })


    }
}