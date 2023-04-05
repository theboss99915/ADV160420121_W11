package com.anmp.adv160420121week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.anmp.adv160420121week4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "fetch"
    private var queue: RequestQueue? = null

    fun fetch(inputID : String) {
        loadingLD.value = true
        studentLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url2 = "http://adv.jitusolution.com/student.php?id="+inputID
        val stringRequest = StringRequest(
            Request.Method.GET, url2,
            {
                val sType = object : TypeToken<Student>() { }.type
                val result = Gson().fromJson<Student>(it, sType)
                val resultNew = result as Student
                studentLD.value = resultNew
                loadingLD.value = false

                Log.d("fetch", result.toString())

            },
            {
                Log.d("fetch", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}