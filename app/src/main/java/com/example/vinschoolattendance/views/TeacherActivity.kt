package com.example.vinschoolattendance

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.vinschoolattendance.views.TeacherProfileFragment
import com.example.vinschoolattendance.views.TeacherScheduleFragment

import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : AppCompatActivity() {
    private val PROFILE: String = "profile"
    private val SCHEDULE: String = "schedule"
    private var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val fragmentManager = supportFragmentManager
        //
        cn_bottom_menu.setItemSelected(R.id.t_schedule, true)
        setTabNavigation(fragmentManager, SCHEDULE)
        //
        cn_bottom_menu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.t_profile -> {
                    setTabNavigation(fragmentManager, PROFILE)
                    Log.d("vy","profile_teacher")
                }
                R.id.t_schedule -> {
                    setTabNavigation(fragmentManager, SCHEDULE)
                    Log.d("vy","schedule_teacher")
                }
            }
        }
    }

    private fun setTabNavigation(fragmentManager: FragmentManager, tab: String) {
        when (tab) {
            PROFILE -> {
                fragment = TeacherProfileFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment!!)
                    .commit()

            }
            SCHEDULE -> {
                fragment = TeacherScheduleFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment!!)
                    .commit()
            }
        }
    }

}
