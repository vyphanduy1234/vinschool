package com.example.vinschoolattendance.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.StudentClassAdapter
import com.example.vinschoolattendance.models.entities.StudentOfClass
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.viewmodels.ClassAttendaceViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.fragment_teacher_schedule.*

class ClassAttendanceActivity : AppCompatActivity(), IBaseView,
    StudentClassAdapter.onAttendanceChange {
    lateinit private var mViewModel: ClassAttendaceViewModel
    private var listStudent: MutableList<StudentOfClass> = mutableListOf()
    lateinit private var studentClassAdapter: StudentClassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_edit_student_attendance)
        initRecycleView()

    }

    private fun initRecycleView() {
        var recycleView: RecyclerView = findViewById(R.id.rcv_teacher_edit_student)
        studentClassAdapter = StudentClassAdapter(listStudent, this, this)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = studentClassAdapter

        setUpViewModel()
    }

    override fun changeToAttend(sid: Int, scheduleId: Int) {

    }

    override fun changeToNotAttend(sid: Int, scheduleId: Int) {
    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this)
            .get(ClassAttendaceViewModel::class.java)

        val scheduleID: Int = intent.getIntExtra("schedule_id", -1)
        mViewModel.loadClassSchedule(scheduleID)
        mViewModel.getListClassAttendance().observe(this, Observer {
            studentClassAdapter.listStudentClass = it
            studentClassAdapter.notifyDataSetChanged()
            progress_bar.visibility = View.GONE
        })

        mViewModel.getInternetStatus().observe(this, Observer<Int> { error ->
            if (error == Network.NETWORK_CONNECT_ERROR) {
                progress_bar.visibility = View.GONE
                tv_internet_error.visibility = View.VISIBLE
            } else {
                tv_internet_error.visibility = View.INVISIBLE
            }
        })
    }
}
