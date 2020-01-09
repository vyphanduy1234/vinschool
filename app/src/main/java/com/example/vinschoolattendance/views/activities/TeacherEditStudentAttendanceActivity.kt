package com.example.vinschoolattendance.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.StudentClassAdapter
import com.example.vinschoolattendance.models.entities.StudentOfClass
import com.example.vinschoolattendance.views.base.IBaseView

class TeacherEditStudentAttendanceActivity : AppCompatActivity(),IBaseView,StudentClassAdapter.onAttendanceChange {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_edit_student_attendance)
        initRecycleView()

    }

    private fun initRecycleView(){
        var list = mutableListOf<StudentOfClass>()
        list.add(StudentOfClass())
        list.add(StudentOfClass())
        list.add(StudentOfClass())
        var recycleView: RecyclerView = findViewById(R.id.rcv_teacher_edit_student)
        var studentClassAdapter = StudentClassAdapter(list,this)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = studentClassAdapter
    }

    override fun changeToAttend(sid: Int, scheduleId: Int) {
        Log.d("TeacherEditStudentAttendanceActivity changeToAttend","sid: ${sid} , ScheduleId: ${scheduleId}")
    }

    override fun changeToNotAttend(sid: Int, scheduleId: Int) {
        Log.d("TeacherEditStudentAttendanceActivity changeToNotAttend","sid: ${sid} , ScheduleId: ${scheduleId}")
    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUpViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
