package com.linkedlist.pagingstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.linkedlist.pagingstudy.data.Document
import com.linkedlist.pagingstudy.data.RemotePagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val documentsFlow = Pager(
        PagingConfig(pageSize = 10)
    ) {
        RemotePagingSource(query = "카카오")
    }.flow.cachedIn(viewModelScope)
    private val _list: MutableStateFlow<List<Document>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<Document>> = _list.asStateFlow()

//    fun fetchData() {
//        viewModelScope.launch {
//            documentsFlow.collect {
//                _list.emit(it)
//            }
//        }
//    }
}