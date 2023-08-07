package com.linkedlist.pagingstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = RetrofitFactory.create<KakaoApi>()

    fun getData() {
        viewModelScope.launch {
            println("요청")
            api.request()
        }
    }
}