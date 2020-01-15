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



    constructor(studentId: Int, StudentAvatarLink: String,
                isAttended: Boolean, fullName: String
                ) {
        this.studentId = studentId
        this.StudentAvatarLink = StudentAvatarLink
        this.isAttended = isAttended
        this.fullName = fullName
    }

    fun toStudentOfClass(id: Int): StudentOfClass{
        var soc = StudentOfClass()
        soc.id = this.studentId
        soc.avatarSource = this.StudentAvatarLink
        soc.name = this.fullName
        soc.isAttend = this.isAttended
        return soc
    }
}