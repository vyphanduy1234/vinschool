package com.example.vinschoolattendance.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.creativelogindesigns.model.StudentSchedule

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.StudentScheduleAdapter
import com.example.vinschoolattendance.repositories.StudentScheduleRepo
import kotlinx.android.synthetic.main.fragment_student_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class StudentScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_student_schedule, container, false)
        initRecyleView(view)
        return view
    }

    fun initRecyleView(view: View){
        var studentScheduleRepo = StudentScheduleRepo()
        var listSchedule: MutableList<StudentSchedule> = mutableListOf()
        var studentScheduleAdapter = StudentScheduleAdapter(listSchedule,context!!)
        val scRepo = StudentScheduleRepo()
        scRepo.fetchStudentSchedule(studentScheduleAdapter)
        var recyclerView: RecyclerView = view.findViewById(R.id.rcv_student_schedule)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = studentScheduleAdapter
    }
}
