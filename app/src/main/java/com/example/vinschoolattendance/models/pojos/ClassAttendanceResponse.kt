package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class ClassAttendanceResponse {
    @SerializedName("student_id")
    var studentId: Int
    @SerializedName("student_avatar_link")
    var StudentAvatarLink: String
    @SerializedName("is_attended")
    var isAttended: String
    @SerializedName("full_name")
    var fullName: String

    constructor(studentId: Int, StudentAvatarLink: String, isAttended: String, fullName: String) {
        this.studentId = studentId
        this.StudentAvatarLink = StudentAvatarLink
        this.isAttended = isAttended
        this.fullName = fullName
    }
}