package com.example.vinschoolattendance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsnipp.creativelogindesigns.api.ApiUtils
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.models.pojos.LoginRequest
import com.example.vinschoolattendance.models.pojos.UserResponse
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.utils.UserAuthen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LoginViewModel: BaseViewModel() {


    companion object{
        val LOGIN_SUCCESS: Int = 1
        val LOGIN_FAIL: Int = 0
        val ROLE_TEACHER: String = "ROLE_TEACHER"
        val ROLE_STUDENT: String = "ROLE_STUDENT"
    }

    private var _loginStatus: MutableLiveData<Int> = MutableLiveData()
    private var _account: MutableLiveData<UserResponse> = MutableLiveData()
    private var _isTeacherLogin: MutableLiveData<Int> = MutableLiveData()
    private var _isStudentLogin: MutableLiveData<Int> = MutableLiveData()

    fun getLoginStatus() = _loginStatus
    fun getAccount() = _account
    fun isTeacherLogin() = _isTeacherLogin
    fun isStudentLogin() = _isStudentLogin

    fun login(loginRequest: LoginRequest){
        ApiUtils.getApiService().login(loginRequest)
            .timeout(Network.NETWORK_CONNECT_TIME_OUT,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //luu id va token
                UserAuthen.ID = it.id
                UserAuthen.Token = it.token
                //kiem tra role la hs hay gv
                for(item in it.roles){
                    if(item.equals(ROLE_TEACHER)){
                        _isTeacherLogin.value = 1
                            break
                    }
                    if(item.equals(ROLE_STUDENT)){
                        _isStudentLogin.value = 1
                        break
                    }
                }
                _loginStatus.value = LOGIN_SUCCESS
            },{
                this.onConnectError(it)
                _loginStatus.value = LOGIN_FAIL
            })
    }
}