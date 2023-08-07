package com.linkedlist.pagingstudy.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class RemotePagingSource(
    private val api: KakaoApi = RetrofitFactory.create(),
    private val query: String
) : PagingSource<Int, Document>() {

    override fun getRefreshKey(state: PagingState<Int, Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        val nextPageNumber = params.key ?: 1
        val response = api.getDocuments()
        return LoadResult.Page(
            data = response.documents,
            prevKey = null,
            nextKey = nextPageNumber + 1
        )
    }
}