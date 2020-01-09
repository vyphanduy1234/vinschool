package com.example.vinschoolattendance.views.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
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
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.fragment_teacher_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class TeacherScheduleFragment : Fragment(), IBaseView {

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
        var view: View = inflater.inflate(R.layout.fragment_teacher_schedule, container, false)
        mProgressBar = view.findViewById(R.id.progress_bar)
        tvErrorLoading = view.findViewById(R.id.tv_internet_error)
        mProgressBar.visibility = View.VISIBLE
        tvErrorLoading.visibility = View.INVISIBLE
        initRecycleView(view!!)
        setUpViewModel()
        return view
    }

    private fun initRecycleView(view: View) {
        mRecyclerView = view.findViewById(R.id.rcv_teacher_schedule)
       var list = mutableListOf<TeacherSchedule>()
        mTeacherScheduleAdapter = TeacherScheduleAdapter(list, context!!)
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

}
