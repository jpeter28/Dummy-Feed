package com.peterj.feed.data

sealed class FeedResponse<out T> {
    data class Success<out T>(val data: T) : FeedResponse<T>()
    data class Error(val message: String) : FeedResponse<Nothing>()
}