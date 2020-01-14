package com.example.vinschoolattendance

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vinschoolattendance.models.pojos.LoginRequest
import com.example.vinschoolattendance.viewmodels.LoginViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), IBaseView {
    private val TAG = "LoginActivity"
    lateinit var mViewModel: LoginViewModel
    private var loginViewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpViewModel()
        initEvent()
    }

    override fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        mViewModel.isStudentLogin().observe(this, Observer {
            val intent = Intent(this@LoginActivity, StudentActivity::class.java)
            startActivity(intent)
        })

        mViewModel.isTeacherLogin().observe(this, Observer {
            val intent = Intent(this@LoginActivity, TeacherActivity::class.java)
            startActivity(intent)
        })

        mViewModel.getLoginStatus().observe(this, Observer {
            when (it) {
                LoginViewModel.LOGIN_SUCCESS -> {

                }
                LoginViewModel.LOGIN_FAIL -> {

                }
            }
        })
    }

    override fun initEvent() {
        //event
        //login event
        btn_login.setOnClickListener {
            if (!tv_email.text.toString().equals("") && !tv_password.toString().equals("")
            ) {
                mViewModel.login(
                    LoginRequest(
                        tv_email.text.toString(),
                        tv_password.text.toString()
                    )
                )
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}