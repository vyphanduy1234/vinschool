package com.example.vinschoolattendance.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

object ScheduleStatus {

    /**
     * kiểm tra 1 tiết học đã bắt đầu hay chưa
     * */
     fun isStarted(time: String, date: String): Boolean {
        val mDate =
            LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy",
                    Locale.ENGLISH
                )
            )
        val today = LocalDate.now()
        if (mDate.isBefore(today)){
            return true
        }
        if (mDate.isEqual(today)) {
            val mTime =
                LocalTime.parse(
                    time,
                    DateTimeFormatter.ofPattern(
                        "HH:mm:ss",
                        Locale.ENGLISH
                    )
                )
            val now = LocalTime.now()
            if (now.hour > mTime.hour) {
                return true
            }else if(mTime.hour == now.hour && now.minute > mTime.minute){
                return true
            }
        }
        return false
    }

    /**
     * kiểm tra sinh viên còn quyền điểm danh cho 1 tiết học hay không
     * */
     fun canTakeAttend(time: String, date: String): Boolean {
        val mDate =
            LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy",
                    Locale.ENGLISH
                )
            )
        val today = LocalDate.now()
        if (mDate.isEqual(today)) {
            val mTime =
                LocalTime.parse(
                    time,
                    DateTimeFormatter.ofPattern(
                        "HH:mm:ss",
                        Locale.ENGLISH
                    )
                )
            val now = LocalTime.now()
            if (mTime.hour == now.hour) {
                if (now.minute <= mTime.minute + 5) {
                    return true
                }
            }
        }
        return false
    }
}