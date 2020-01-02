package com.appsnipp.creativelogindesigns.api

object ApiUtils {
    private val STAGE = ""

    @JvmStatic
    fun getApiService(): ApiServices{
        return RetrofitClient.getClient(STAGE)!!.create(ApiServices::class.java)
    }
}