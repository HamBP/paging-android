package com.linkedlist.pagingstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.linkedlist.pagingstudy.data.Document
import com.linkedlist.pagingstudy.data.RemotePagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class MainViewModel : ViewModel() {
    private val _keyword = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val documentsFlow: Flow<PagingData<Document>> =
        keyword.debounce(300L).flatMapLatest { createPager(it) }

    fun updateKeyword(newKeyword: String) {
        _keyword.value = newKeyword
    }

    private fun createPager(query: String): Flow<PagingData<Document>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            RemotePagingSource(query = query)
        }.flow.cachedIn(viewModelScope)
    }
}
