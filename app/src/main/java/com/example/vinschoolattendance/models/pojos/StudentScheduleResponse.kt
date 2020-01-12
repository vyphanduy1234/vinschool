package com.example.vinschoolattendance.models.pojos

import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.google.gson.annotations.SerializedName
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class StudentScheduleResponse {
    @SerializedName("time_start")
    var timeStart: String

    @SerializedName("present")
    var isAttend: Boolean

    @SerializedName("subject")
    var subject: String

    @SerializedName("teacher")
    var teacher: String

    @SerializedName("room")
    var room: String

    @SerializedName("avatar_5_friends")
    var listStudentAttend: MutableList<String>

    @SerializedName("class_name")
    var cclass: String

    @SerializedName("date")
    var date: String

    constructor(
        timeStart: String,
        isAttend: Boolean,
        subject: String,
        teacher: String,
        room: String,
        listStudentAttend: MutableList<String>,
        cclass: String,
        date: String
    ) {
        this.timeStart = timeStart
        this.isAttend = isAttend
        this.subject = subject
        this.teacher = teacher
        this.room = room
        this.listStudentAttend = listStudentAttend
        this.cclass = cclass
        this.date = date
    }

    open fun toStudentSchedule(): StudentSchedule {
        val sc = StudentSchedule()
        sc.cclass = this.cclass
        sc.isAttend = this.isAttend
        sc.subject = this.subject
        val time =
            LocalTime.parse(
                this.timeStart,
                DateTimeFormatter.ofPattern(
                    "HH:mm:ss",
                    Locale.ENGLISH
                )
            )
        sc.timeStartHour = time.hour
        sc.timeStartMinute = time.minute
        sc.timeStartAT = if (sc.timeStartHour < 12) "AM" else "PM"
        sc.subjectTime = "1 hour 30 minutes"
        sc.teacher = this.teacher
        sc.friendAttends = listStudentAttend
        sc.date = this.date
        sc.timeStart = this.timeStart
        return sc
    }
}