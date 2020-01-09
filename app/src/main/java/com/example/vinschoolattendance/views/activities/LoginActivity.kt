package com.example.vinschoolattendance

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.vinschoolattendance.viewmodels.LoginViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*

class LoginActivity : AppCompatActivity(), IBaseView {
    private val TAG = "LoginActivity"
    private var loginViewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initEvent()
    }

    override fun setUpViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun initEvent(){
        //event
        tv_register.setOnClickListener{
            layout_login.visibility = View.GONE
            layout_register.visibility = View.VISIBLE
        }
        //event
        tv_back_to_login.setOnClickListener {
            layout_login.visibility = View.VISIBLE
            layout_register.visibility = View.GONE
        }
        //login event
        btn_login.setOnClickListener {
            if(tv_email.text.toString() == "1"){
                val intent = Intent(this@LoginActivity,TeacherActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@LoginActivity,StudentActivity::class.java)
                startActivity(intent)
            }

        }
        //event
        btn_register.setOnClickListener {

        }

    }
}