package com.example.vinschoolattendance.views

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vinschoolattendance.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_teacher_take_attendace.*

class TeacherTakeAttendanceActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_take_attendace)

        btn_generate.setOnClickListener(){view -> run{
            val text: String? = edt_text.text.toString()

            if(text != null){
                var multiFomat: MultiFormatWriter = MultiFormatWriter()
                var bitMatrix: BitMatrix = multiFomat.encode(text,BarcodeFormat.QR_CODE,img_qr.width,img_qr.height)
                val barcodeEncoder: BarcodeEncoder = BarcodeEncoder()
                var bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
                img_qr.setImageBitmap(bitmap)
            }
        }}

        btn_scan.setOnClickListener(){view -> run{

            val intent: Intent = Intent(this@TeacherTakeAttendanceActivity,StudentTakeAttendanceActivity::class.java)
            startActivity(intent)
        }}
    }
}
