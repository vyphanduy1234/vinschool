package com.example.vinschoolattendance.views.fragment


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.adapters.StudentScheduleAdapter
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.utils.DateTime
import com.example.vinschoolattendance.utils.Loader
import com.example.vinschoolattendance.utils.UserAuthen
import com.example.vinschoolattendance.viewmodels.StudentViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.fragment_student_schedule.view.*

import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class StudentScheduleFragment : Fragment(), IBaseView {
    lateinit var mStudentViewModel: StudentViewModel

    var mListSchedule: MutableList<StudentSchedule> = mutableListOf()

    lateinit var mStudentScheduleAdapter: StudentScheduleAdapter

    lateinit var mView: View

    lateinit var mRecyclerView: RecyclerView

    lateinit var tvErrorLoading: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_student_schedule, container, false)
        tvErrorLoading = mView.findViewById(R.id.tv_internet_error)

        Loader.showLoader(activity!!.supportFragmentManager)

        tvErrorLoading.visibility = View.INVISIBLE

        initEvent()
        initRecyleView()
        setUpViewModel()
        return mView
    }

    fun initRecyleView() {
        mStudentScheduleAdapter = StudentScheduleAdapter(mListSchedule, context!!)
        mRecyclerView = mView.findViewById(R.id.rcv_student_schedule)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mStudentScheduleAdapter
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun initEvent() {
        val cldr: Calendar = Calendar.getInstance()
        val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
        val month: Int = cldr.get(Calendar.MONTH)
        val year: Int = cldr.get(Calendar.YEAR)

        mView.btn_pick_a_date.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                context,
                OnDateSetListener { datePicker, year, month, day ->
                    val date = DateTime.NormalizeDate(year, month, day)
                    mStudentViewModel.loadStudentSchedule(UserAuthen.ID,date)

                    Loader.showLoader(activity!!.supportFragmentManager)
                }, year, month, day
            )
            datePickerDialog.show()
        }

    }

    override fun setUpViewModel() {
        mStudentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        //load data tu server ve
        val today: String = DateTime.getDateToday()
        mStudentViewModel.loadStudentSchedule(UserAuthen.ID,today)
        // mStudentScheduleAdapter.listSchedule = mStudentViewModel.getListStudentSchedule().value
        //lang nghe su kien thay doi
        val studentScheduleObserver = Observer<MutableList<StudentSchedule>> { StudentSchedule ->
            mStudentScheduleAdapter.listSchedule = StudentSchedule
            mStudentScheduleAdapter.notifyDataSetChanged()
            Loader.hideLoader(activity!!.supportFragmentManager)
        }
        mStudentViewModel.getListStudentSchedule().observe(this, studentScheduleObserver)

        mStudentViewModel.getInternetStatus().observe(this, Observer<Int> { error ->
            if (error == Network.NETWORK_CONNECT_ERROR) {
                Loader.hideLoader(activity!!.supportFragmentManager)
                tvErrorLoading.visibility = View.VISIBLE
            } else {
                Loader.hideLoader(activity!!.supportFragmentManager)
                tvErrorLoading.visibility = View.INVISIBLE
            }
        })
    }
}
