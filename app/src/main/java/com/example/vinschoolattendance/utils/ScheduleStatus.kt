package com.example.vinschoolattendance.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

object ScheduleStatus {
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
            if (mTime.hour <= now.hour && mTime.minute<= now.minute) {
                return true
            }
        }
        return false
    }

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
                if (mTime.minute + 10 <= now.minute) {
                    return true
                }
            }
        }
        return false
    }
}