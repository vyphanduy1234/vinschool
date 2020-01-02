package com.appsnipp.creativelogindesigns.api

import com.appsnipp.creativelogindesigns.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices{
    
    @get:GET("")
    val login: Call<User?>?

    }