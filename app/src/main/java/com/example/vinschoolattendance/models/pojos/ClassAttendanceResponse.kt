package com.example.vinschoolattendance.models.pojos

import com.example.vinschoolattendance.models.entities.StudentOfClass
import com.google.gson.annotations.SerializedName

class ClassAttendanceResponse {
    @SerializedName("student_id")
    var studentId: Int

    @SerializedName("avatar")
    var StudentAvatarLink: String

    @SerializedName("is_present")
    var isAttended: Boolean

    @SerializedName("name")
    var fullName: String

    @SerializedName("schedule_id")
    var scheduleId: Int

    constructor(studentId: Int, StudentAvatarLink: String,
                isAttended: Boolean, fullName: String
                ,scheduleId: Int) {
        this.studentId = studentId
        this.StudentAvatarLink = StudentAvatarLink
        this.isAttended = isAttended
        this.fullName = fullName
        this.scheduleId = scheduleId
    }

    fun toStudentOfClass(id: Int): StudentOfClass{
        var soc = StudentOfClass()
        soc.id = this.studentId
        soc.scheduleId = this.scheduleId
        soc.avatarSource = this.StudentAvatarLink
        soc.name = this.fullName
        soc.isAttend = this.isAttended
        return soc
    }
}