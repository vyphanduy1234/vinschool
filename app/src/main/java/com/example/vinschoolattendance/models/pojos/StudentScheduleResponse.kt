package com.example.vinschoolattendance.models.pojos

import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.models.entities.FriendAttend
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class StudentScheduleResponse {
    @SerializedName("time_start")
    var timeStart: String
    @SerializedName("isAttend")
    var isAttend: Boolean
    @SerializedName("subject")
    var subject: String
    @SerializedName("teacher")
    var teacher: String
    @SerializedName("room")
    var room: String
    @SerializedName("list_student_attend")
    var listStudentAttend: MutableList<FriendAttend>
    @SerializedName("class")
    var cclass: String

    constructor(
        timeStart: String,
        isAttend: Boolean,
        subject: String,
        teacher: String,
        room: String,
        listStudentAttend: MutableList<FriendAttend>,
        cclass: String
    ) {
        this.timeStart = timeStart
        this.isAttend = isAttend
        this.subject = subject
        this.teacher = teacher
        this.room = room
        this.listStudentAttend = listStudentAttend
        this.cclass = cclass
    }


    open fun toStudentSchedule(): StudentSchedule{
        val sc = StudentSchedule()
        sc.cclass = this.cclass
        //sc.friendAttends = this.friendAttends?.toMutableList()
        sc.isAttend = this.isAttend
        sc.subject = this.subject
        sc.timeStartHour = LocalDateTime.parse(this.timeStart.substring(0,19),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)).hour
        sc.timeStartMinute = LocalDateTime.parse(this.timeStart.substring(0,19),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)).minute
        sc.timeStartAT = if(sc.timeStartHour < 12) "AM" else "PM"
        sc.subjectTime = "1 hour 30 minutes"

        return sc
    }
}