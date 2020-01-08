package com.example.vinschoolattendance.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.StudentClassAdapter
import com.example.vinschoolattendance.models.entities.StudentOfClass

class TeacherEditStudentAttendance : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_edit_student_attendance)
        initRecycleView()

    }

    private fun initRecycleView(){
        val studentClass =
            StudentOfClass()
        val studentClass2 =
            StudentOfClass()
        studentClass2.isAttend = false
        var list = mutableListOf<StudentOfClass>()
        list.add(studentClass)
        list.add(studentClass2)
        var recycleView: RecyclerView = findViewById(R.id.rcv_teacher_edit_student)
        var studentClassAdapter = StudentClassAdapter(list)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = studentClassAdapter
    }
}
