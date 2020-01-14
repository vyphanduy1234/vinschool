package com.example.vinschoolattendance.utils

import java.time.LocalDate

object DateTime {

    fun getDateToday(): String {
        val today = LocalDate.now()
        val year = today.year.toString()
        var month: String = (today.monthValue).toString()
        var day: String = today.dayOfMonth.toString()
        month = if(month.length == 1) "0" + month else month
        day = if(day.length == 1) "0" + day else day
        return "$year-$month-$day"
    }

    fun NormalizeDate(year: Int, month: Int, day: Int): String{
        val month2 = month +1
        val mMonth: String = if(month2.toString().length == 1) "0" + month2 else "$month2"
        val mDay: String = if(day.toString().length == 1) "0" + day else "$day"

        return "$year-$mMonth-$mDay"
    }
}