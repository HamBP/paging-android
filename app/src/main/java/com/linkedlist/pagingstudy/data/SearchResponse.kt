package com.linkedlist.pagingstudy.data

data class SearchResponse<T>(
    val meta: Meta,
    val documents: List<T>
)

data class Document(
    val title: String
)