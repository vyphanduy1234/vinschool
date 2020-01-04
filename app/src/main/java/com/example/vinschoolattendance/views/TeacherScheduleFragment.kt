package com.example.vinschoolattendance.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.TeacherScheduleAdapter
import com.example.vinschoolattendance.models.TeacherSchedule

/**
 * A simple [Fragment] subclass.
 */
class TeacherScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_teacher_schedule, container, false)
        initRecycleView(view!!)
        return view
    }

    private fun initRecycleView(view: View){
        var recycleView: RecyclerView = view.findViewById(R.id.rcv_teacher_schedule)
        val teacherSchedule: TeacherSchedule = TeacherSchedule()
        var list = mutableListOf<TeacherSchedule>()
        list.add(teacherSchedule)

        var teacherScheduleAdapter: TeacherScheduleAdapter = TeacherScheduleAdapter(list,context!!)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = teacherScheduleAdapter
    }


}
