package com.linkedlist.pagingstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = RetrofitFactory.create<KakaoApi>()
    private val _list: MutableStateFlow<List<Document>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<Document>> = _list.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            _list.value = api.request().documents
        }
    }
}