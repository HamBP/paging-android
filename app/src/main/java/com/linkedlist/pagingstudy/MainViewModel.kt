package com.linkedlist.pagingstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.linkedlist.pagingstudy.data.RemotePagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    val documentsFlow = Pager(
        PagingConfig(pageSize = 10)
    ) {
        RemotePagingSource(query = keyword.value)
    }.flow.cachedIn(viewModelScope)

    private val _keyword = MutableStateFlow("히")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    fun updateKeyword(newKeyword: String) {
        _keyword.value = newKeyword
    }
}