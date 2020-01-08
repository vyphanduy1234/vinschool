package com.appsnipp.creativelogindesigns.api

import com.appsnipp.creativelogindesigns.model.StudentSchedule

object ApiUtils {
    private val STAGE = "https://attendancecapstone.herokuapp.com/"

    @JvmStatic
    fun getApiService(): ApiServices{
        return RetrofitClient.getClient(STAGE)!!.create(ApiServices::class.java)

    }
}