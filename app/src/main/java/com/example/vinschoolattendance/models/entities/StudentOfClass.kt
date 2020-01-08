package com.example.vinschoolattendance.models.entities

import com.example.vinschoolattendance.R

class StudentOfClass(var id: Int,
                     var avatarSource: Int = R.drawable.icon_age,
                     var name: String = "Phan Duy Vy",
                     var isAttend: Boolean = true) {

}