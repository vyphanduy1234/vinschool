package com.example.vinschoolattendance.models.pojos

import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.google.gson.annotations.SerializedName
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class TeacherScheduleResponse {
    @SerializedName("time_start")
    var timeStart: String

    @SerializedName("class_name")
    var cclass: String

    @SerializedName("subject")
    var subject: String

    @SerializedName("room")
    var room: String

    @SerializedName("date")
    var date: String

    @SerializedName("total")
    var classTotalStudent: Int

    @SerializedName("total_attendance")
    var classTotalStudentAttend: Int

    @SerializedName("schedule_id")
    var scheduleId: Int

    constructor(
        timeStart: String,
        cclass: String,
        subject: String,
        room: String,
        classTotalStudent: Int,
        classTotalStudentAttend: Int,
        scheduleId: Int,
        date: String
    ) {
        this.timeStart = timeStart
        this.cclass = cclass
        this.subject = subject
        this.room = room
        this.classTotalStudent = classTotalStudent
        this.classTotalStudentAttend = classTotalStudentAttend
        this.scheduleId = scheduleId
        this.date = date
    }

    fun toTeacherSchedule(): TeacherSchedule {

        var teacherSchedule = TeacherSchedule()

        teacherSchedule.cclass = this.cclass
        teacherSchedule.subject = this.subject
        val time =
            LocalTime.parse(
                this.timeStart,
                DateTimeFormatter.ofPattern(
                    "HH:mm:ss",
                    Locale.ENGLISH
                )
            )
        teacherSchedule.timeHour = time.hour
        teacherSchedule.timeMinute = time.minute
        teacherSchedule.timeAT = if (teacherSchedule.timeHour < 12) "AM" else "PM"
        teacherSchedule.room = this.room
        teacherSchedule.scheduleId = scheduleId
        teacherSchedule.totalStudent = this.classTotalStudent
        teacherSchedule.attendStudent = this.classTotalStudentAttend
        teacherSchedule.date = this.date
        teacherSchedule.timeStart = this.timeStart
        return teacherSchedule
    }
}