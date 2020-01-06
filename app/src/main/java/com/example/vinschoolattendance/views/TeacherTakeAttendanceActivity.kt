package com.example.vinschoolattendance.views

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.vinschoolattendance.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_teacher_take_attendace.*

class TeacherTakeAttendanceActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"
    val player: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_take_attendace)

        btn_check_attend.setOnClickListener {
            val intent = Intent(this,TeacherEditStudentAttendance::class.java)
            startActivity(intent)
        }

        btn_take.setOnClickListener(){view -> run{
            val text: String? = "VyDZ"
            tv_time_notify.text = "QR will be shown in"
            tv_timer.visibility = View.VISIBLE
            val takeAttendanceTimmer = object: CountDownTimer(50000,1000){
                override fun onTick(p0: Long) {
                    val time = p0/1000
                    tv_timer.text = time.toString()
                    tv_timer.text = time.toString()
                }

                override fun onFinish() {
                    tv_time_notify.text = "Time over"
                    btn_check_attend.isEnabled = true
                    img_qr.visibility = View.INVISIBLE
                }
            }

            val startTimer = object : CountDownTimer(10000,1000){
                override fun onTick(p0: Long) {
                    val time = p0/1000
                    tv_timer.text = time.toString()
                }

                override fun onFinish() {
                    text?.let {
                        var multiFomat: MultiFormatWriter = MultiFormatWriter()
                        var bitMatrix: BitMatrix = multiFomat.encode(text,BarcodeFormat.QR_CODE,img_qr.width,img_qr.height)
                        val barcodeEncoder: BarcodeEncoder = BarcodeEncoder()
                        var bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
                        img_qr.setImageBitmap(bitmap)
                    }
                    tv_time_notify.text = "Time to scan"
                    btn_take.isEnabled = false
                    takeAttendanceTimmer.start()
                }
            }
            startTimer.start()

        }}
    }
}
