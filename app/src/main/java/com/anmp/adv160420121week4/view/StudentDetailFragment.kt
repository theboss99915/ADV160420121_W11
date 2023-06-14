package com.anmp.adv160420121week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anmp.adv160420121week4.R
import com.anmp.adv160420121week4.databinding.FragmentStudentDetailBinding
import com.anmp.adv160420121week4.model.Student
import com.anmp.adv160420121week4.util.loadImage
import com.anmp.adv160420121week4.viewmodel.DetailViewModel
import com.anmp.adv160420121week4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.txtID
import kotlinx.android.synthetic.main.student_list_item.txtName
import java.util.concurrent.TimeUnit
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment(),ButtonUpdateClickListener,ButtonNotifClickListener {
//    private lateinit var dataBinding:FragmentEditTodoBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userID = ""
        if(arguments != null) {
            userID = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(userID)
        observeViewModel()

        dataBinding.notif=this
        dataBinding.update=this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
//            txtID.setText(student.id)
//            txtName.setText(student.name)
//            txtBod.setText(student.bod)
//            txtPhone.setText(student.phone)
//
//            var imageView = imageViewDetail
//            var progressBar = progressBarDetail
//
//            imageView.loadImage(student.photoUrl, progressBar)
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_account_circle_24)
//                    }
//            }

        })
    }

    override fun onButtonNotifClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(v.tag.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_account_circle_24)
                    }
    }

    override fun onButtonUpdateClickListener(v: View) {

        var newS = Student(txtID.text.toString(),txtName.text.toString(),txtBod.text.toString(),txtPhone.text.toString(),"")
        Log.d("Bob",newS.toString())
    }

}