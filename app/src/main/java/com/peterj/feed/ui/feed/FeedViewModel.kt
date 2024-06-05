package com.peterj.feed.ui.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterj.feed.data.FeedRepository
import com.peterj.feed.data.FeedResponse
import com.peterj.feed.data.model.Feed
import com.peterj.feed.data.network.NetworkFeedService
import com.peterj.feed.data.network.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel() : ViewModel() {
    private val networkFeedService = RetrofitService.getInstance().create(NetworkFeedService::class.java)
    private val repository = FeedRepository(feedService = networkFeedService)
    private val _feedFlow = MutableStateFlow<List<Feed>>(emptyList())
    val feedList: StateFlow<List<Feed>> get() = _feedFlow

    init {
        getFeeds()
    }

    private fun getFeeds() {
        viewModelScope.launch {
            when (val result = repository.getFeeds()) {
                is FeedResponse.Success -> {
                    val currentList = _feedFlow.value.toMutableList()
                    currentList.addAll(result.data)
                    _feedFlow.value = currentList
                }

                is FeedResponse.Error -> {
                    Log.e("Error:", result.message)
                }
            }
        }
    }
}