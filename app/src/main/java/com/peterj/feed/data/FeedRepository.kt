package com.peterj.feed.data

import com.peterj.feed.data.model.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeedRepository(private val feedService: FeedService) {

    suspend fun getFeeds(limit: Int = 30, skip: Int = 0) : FeedResponse<List<Feed>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = feedService.getFeeds(limit = limit, skip = skip)
                FeedResponse.Success(result.products)
            } catch (e: Exception) {
                FeedResponse.Error("Error: ${e.message}")
            }
        }
    }
}