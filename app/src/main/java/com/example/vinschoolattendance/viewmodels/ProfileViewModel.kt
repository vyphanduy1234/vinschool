package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinschoolattendance.models.base.BaseProfile
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.HelperRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ProfileViewModel: BaseViewModel() {

    private var _userInfo: MutableLiveData<BaseProfile> = MutableLiveData()

    fun getUserInfo() = _userInfo

}