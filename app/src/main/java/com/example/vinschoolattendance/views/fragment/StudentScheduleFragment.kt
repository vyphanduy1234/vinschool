package com.example.vinschoolattendance.views.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.creativelogindesigns.model.StudentSchedule

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.StudentScheduleAdapter
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.repositories.StudentRepository
import com.example.vinschoolattendance.viewmodels.StudentViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.fragment_teacher_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class StudentScheduleFragment : Fragment(), IBaseView {
    lateinit var mStudentViewModel: StudentViewModel
    var mListSchedule: MutableList<StudentSchedule> = mutableListOf()
    lateinit var mStudentScheduleAdapter: StudentScheduleAdapter
    lateinit var mView: View
    lateinit var mRecyclerView: RecyclerView
    lateinit var mProgressBar: ProgressBar
    lateinit var tvErrorLoading: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_student_schedule, container, false)
        mProgressBar = mView.findViewById(R.id.progress_bar)
        tvErrorLoading = mView.findViewById(R.id.tv_internet_error)
        mProgressBar.visibility = View.VISIBLE
        tvErrorLoading.visibility = View.INVISIBLE
        initRecyleView()
        setUpViewModel()
        return mView
    }

    fun initRecyleView() {
        mStudentScheduleAdapter = StudentScheduleAdapter(mListSchedule, context!!)
        mRecyclerView = mView.findViewById(R.id.rcv_student_schedule)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mStudentScheduleAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUpViewModel() {
        mStudentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        //load data tu server ve
        mStudentViewModel.loadStudentSchedule()
        // mStudentScheduleAdapter.listSchedule = mStudentViewModel.getListStudentSchedule().value
        //lang nghe su kien thay doi
        val studentScheduleObserver = Observer<MutableList<StudentSchedule>> { StudentSchedule ->
            mStudentScheduleAdapter.listSchedule = StudentSchedule
            mStudentScheduleAdapter.notifyDataSetChanged()
            mProgressBar.visibility = View.GONE
        }
        mStudentViewModel.getListStudentSchedule().observe(this, studentScheduleObserver)

        mStudentViewModel.getInternetStatus().observe(this,Observer<Int>{error ->
            if(error == Network.NETWORK_CONNECT_ERROR){
                progress_bar.visibility = View.GONE
                tvErrorLoading.visibility = View.VISIBLE
            }else{
                tvErrorLoading.visibility = View.INVISIBLE
            }
        })
    }
}