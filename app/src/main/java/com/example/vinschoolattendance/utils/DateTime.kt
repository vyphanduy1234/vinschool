package com.example.vinschoolattendance.utils

import java.time.LocalDate

object DateTime {

    /**
     * lấy thông tin về ngày hôm nay
     * @return thông tin ngày hôm nay dưới định dạng yyyy-MM-dd
     * */
    fun getDateToday(): String {
        val today = LocalDate.now()
        val year = today.year.toString()
        var month: String = (today.monthValue).toString()
        var day: String = today.dayOfMonth.toString()
        month = if(month.length == 1) "0" + month else month
        day = if(day.length == 1) "0" + day else day
        return "$year-$month-$day"
    }
    /**
     * chuẩn hóa lại ngày tháng năm
     * @property year: năm
     * @property month: tháng
     * @property day: ngày
     * @return thông tin ngày truyền vào dưới định dạng yyyy-MM-dd
     * */
    fun NormalizeDate(year: Int, month: Int, day: Int): String{
        val month2 = month +1
        val mMonth: String = if(month2.toString().length == 1) "0" + month2 else "$month2"
        val mDay: String = if(day.toString().length == 1) "0" + day else "$day"

        return "$year-$mMonth-$mDay"
    }
}