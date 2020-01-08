package com.example.vinschoolattendance.models.pojos

import com.example.vinschoolattendance.models.entities.FriendAttend
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.google.gson.annotations.SerializedName

class TeacherScheduleResponse {
    @SerializedName("time_start")
    var timeStart: String
    @SerializedName("class")
    var cclass: String
    @SerializedName("subject")
    var subject: String
    @SerializedName("room")
    var room: String
    @SerializedName("class_total_student")
    var classTotalStudent: Int
    @SerializedName("class_total_student_attend")
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
        scheduleId: Int
    ) {
        this.timeStart = timeStart
        this.cclass = cclass
        this.subject = subject
        this.room = room
        this.classTotalStudent = classTotalStudent
        this.classTotalStudentAttend = classTotalStudentAttend
        this.scheduleId = scheduleId
    }

    fun toTeacherSchedule(): TeacherSchedule{
        return TeacherSchedule()
    }
}