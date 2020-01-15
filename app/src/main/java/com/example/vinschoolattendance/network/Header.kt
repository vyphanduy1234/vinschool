package com.example.vinschoolattendance.network

/**
 * lớp dùng để lưu lại token người dùng
 * */
object Header {
    /**
     * lưu lại token người dùng trong biến  header khi người dùng đăng nhập thành công
     * */
    val header: MutableMap<String,String> = mutableMapOf()
}