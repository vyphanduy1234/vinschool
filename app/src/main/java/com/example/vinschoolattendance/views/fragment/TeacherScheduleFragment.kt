package com.example.vinschoolattendance.views.fragment


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.TeacherScheduleAdapter
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.viewmodels.TeacherViewModel
import com.example.vinschoolattendance.views.activities.ClassAttendanceActivity
import com.example.vinschoolattendance.views.activities.TeacherTakeAttendanceActivity
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.fragment_student_schedule.view.*
import kotlinx.android.synthetic.main.fragment_teacher_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class TeacherScheduleFragment : Fragment(), IBaseView,TeacherScheduleAdapter.TeacherScheduleListener {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mTeacherScheduleAdapter: TeacherScheduleAdapter
    lateinit var mViewModel: TeacherViewModel
    lateinit var mProgressBar: ProgressBar
    lateinit var tvErrorLoading: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mView: View = inflater.inflate(R.layout.fragment_teacher_schedule, container, false)
        mProgressBar = mView.findViewById(R.id.progress_bar)
        tvErrorLoading = mView.findViewById(R.id.tv_internet_error)
        mProgressBar.visibility = View.VISIBLE
        tvErrorLoading.visibility = View.INVISIBLE

        mView.btn_pick_a_date.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    Toast.makeText(context,"year: $year month: $month day: $day", Toast.LENGTH_LONG)
                }, 0, 0, 0
            )
            datePickerDialog.show()
        }

        initRecycleView(mView!!)
        setUpViewModel()
        return mView
    }

    private fun initRecycleView(view: View) {
        mRecyclerView = view.findViewById(R.id.rcv_teacher_schedule)
       var list = mutableListOf<TeacherSchedule>()
        mTeacherScheduleAdapter = TeacherScheduleAdapter(list, context!!,this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mTeacherScheduleAdapter

        mRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUpViewModel() {

        mViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        mViewModel.loadTeacherSchedule()
        val observer: Observer<MutableList<TeacherSchedule>> = Observer<MutableList<TeacherSchedule>> {
            teacherSchedule ->
            mTeacherScheduleAdapter.listSchedule = teacherSchedule
            mTeacherScheduleAdapter.notifyDataSetChanged()
            progress_bar.visibility = View.GONE

        }
        mViewModel.getTeacherSchedule().observe(this,observer)

        mViewModel.getInternetStatus().observe(this,Observer<Int>{error ->
            if(error == Network.NETWORK_CONNECT_ERROR){
                progress_bar.visibility = View.GONE
                tvErrorLoading.visibility = View.VISIBLE
            }else{
                tvErrorLoading.visibility = View.INVISIBLE
            }
        })
    }

    override fun onEditClassAttendance(scheduleId: Int) {
        val intent = Intent(
            context,
            ClassAttendanceActivity::class.java
        )
        intent.putExtra("schedule_id", scheduleId)
        context!!.startActivity(intent)
    }

    override fun onTakeClassAttendance(scheduleId: Int) {
        val intent = Intent(
            context,
            TeacherTakeAttendanceActivity::class.java
        )
        intent.putExtra("schedule_id", scheduleId)
        context!!.startActivity(intent)
    }

}
