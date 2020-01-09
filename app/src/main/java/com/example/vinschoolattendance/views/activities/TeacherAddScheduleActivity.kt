package com.example.vinschoolattendance.views.activities


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.views.base.IBaseView

/**
 * A simple [Fragment] subclass.
 */
class TeacherAddScheduleActivity : Fragment(),IBaseView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_add_schedule, container, false)
    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUpViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
