package com.example.vinschoolattendance.views.fragment


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.viewmodels.HelperViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import androidx.lifecycle.Observer
import com.example.vinschoolattendance.models.pojos.*
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.utils.DateTime
import com.example.vinschoolattendance.utils.Loader
import kotlinx.android.synthetic.main.activity_teacher_take_attendace.*
import kotlinx.android.synthetic.main.fragment_teacher_add_schedule.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class TeacherAddScheduleFragment : Fragment(), IBaseView {

    lateinit private var mView: View
    lateinit private var mTvPickDate: TextView
    lateinit private var mTvRegisterSuccess: TextView
    lateinit private var mTvRegisterFail: TextView
    lateinit private var mBtnRegister: Button
    lateinit private var mViewodel: HelperViewModel


    lateinit var mClassAdapter: ArrayAdapter<ClassResponse>
    lateinit var mRoomAdapter: ArrayAdapter<RoomResponse>
    lateinit var mTermAdapter: ArrayAdapter<TermResponse>
    lateinit var mTeacherAdapter: ArrayAdapter<TeacherResponse>
    lateinit var mSubjectAdapter: ArrayAdapter<SubjectResponse>
    lateinit var mSlotAdapter: ArrayAdapter<SlotResponse>

    lateinit var mSpnClass: Spinner
    lateinit var mSpnRoom: Spinner
    lateinit var mSpnTerm: Spinner
    lateinit var mSpnSubject: Spinner
    lateinit var mSpnTeacher: Spinner
    lateinit var mSpnSlot: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_teacher_add_schedule, container, false)
        mTvPickDate = mView.findViewById(R.id.btn_pick_a_date)

        mSpnClass = mView.findViewById(R.id.spn_class)
        mSpnRoom = mView.findViewById(R.id.spn_room)
        mSpnSubject = mView.findViewById(R.id.spn_subject)
        mSpnTerm = mView.findViewById(R.id.spn_term)
        mSpnTeacher = mView.findViewById(R.id.spn_teacher)
        mSpnSlot = mView.findViewById(R.id.spn_slot)
        mBtnRegister = mView.findViewById(R.id.btn_register)
        mTvRegisterSuccess = mView.findViewById(R.id.tv_register_success)
        mTvRegisterFail = mView.findViewById(R.id.tv_register_fail)

        initEvent()
        setUpViewModel()
        return mView
    }

    override fun initEvent() {
        val cldr: Calendar = Calendar.getInstance()
        val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
        val month: Int = cldr.get(Calendar.MONTH)
        val year: Int = cldr.get(Calendar.YEAR)

        mTvPickDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    val date = DateTime.NormalizeDate(year,month,day)
                    mTvPickDate.text = date
                }, year, month, day
            )
            datePickerDialog.show()
        }

        mBtnRegister.setOnClickListener {
            val registerReq = getSpinerValue()
            Loader.showLoader(activity!!.supportFragmentManager)
            mViewodel.registerSchedule(registerReq)
        }
    }

    override fun setUpViewModel() {
        mViewodel = ViewModelProviders.of(this).get(HelperViewModel::class.java)
        mViewodel.loadResource()

        mViewodel.getRecordStatus().observe(this, Observer {
            when (it) {
                Network.BAD_REQUEST -> {
                    Loader.hideLoader(activity!!.supportFragmentManager)
                    Toast.makeText(context,"Date is invalid",Toast.LENGTH_LONG).show()
                }
            }
        })

        mViewodel.getClass().observe(this, Observer {
            mClassAdapter = ArrayAdapter<ClassResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mSpnClass.adapter = mClassAdapter
            mSpnClass.setSelection(0)
        })

        mViewodel.getRoom().observe(this, Observer {
            mRoomAdapter = ArrayAdapter<RoomResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mSpnRoom.adapter = mRoomAdapter
            mSpnRoom.setSelection(0)
        })

        mViewodel.getSubject().observe(this, Observer {
            mSubjectAdapter = ArrayAdapter<SubjectResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mSpnSubject.adapter = mSubjectAdapter
            mSpnSubject.setSelection(0)
        })

        mViewodel.getTerm().observe(this, Observer {
            mTermAdapter = ArrayAdapter<TermResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mSpnTerm.adapter = mTermAdapter
            mSpnTerm.setSelection(0)
        })

        mViewodel.getTeacher().observe(this, Observer {
            mTeacherAdapter = ArrayAdapter<TeacherResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mSpnTeacher.adapter = mTeacherAdapter
            mSpnTeacher.setSelection(0)
        })

        mViewodel.getSlot().observe(this, Observer {
            mSlotAdapter = ArrayAdapter<SlotResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mSpnSlot.adapter = mSlotAdapter
            mSpnSlot.setSelection(0)
        })

        mViewodel.getRegisterScheduleStatus().observe(this, Observer {
            Loader.hideLoader(activity!!.supportFragmentManager)
            if(it == HelperViewModel.REGISTER_SUCCESS){
                Toast.makeText(context,"Thêm thành công!!",Toast.LENGTH_LONG).show()
//                mTvRegisterFail.visibility = View.INVISIBLE
//                mTvRegisterSuccess.visibility = View.VISIBLE
            }
        })

        mViewodel.getInternetStatus().observe(this, Observer {
            Loader.hideLoader(activity!!.supportFragmentManager)
                Toast.makeText(context,"Thêm thất bại, time out!!",Toast.LENGTH_LONG).show()
//                mTvRegisterFail.visibility = View.INVISIBLE
//                mTvRegisterSuccess.visibility = View.VISIBLE
        })
    }

    fun getSpinerValue(): ScheduleRegisterRequest {
        val date = mTvPickDate.text.toString()
        val classId = (spn_class.selectedItem as ClassResponse).id
        val slotId = (spn_slot.selectedItem as SlotResponse).id
        val roomId = (spn_room.selectedItem as RoomResponse).id
        val teacherId = (spn_teacher.selectedItem as TeacherResponse).id
        val termId = (spn_term.selectedItem as TermResponse).id
        val subjectId = (spn_subject.selectedItem as SubjectResponse).id

        return ScheduleRegisterRequest(
            classId = classId,
            roomId = roomId, subjectId = subjectId, date = date
            , categoryId = slotId, subTrainerId = teacherId, termId = termId
        )
    }

}
