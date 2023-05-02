package com.example.timeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeViewModel : ViewModel(){
    val city = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val time = MutableLiveData<String>()
}