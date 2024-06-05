package com.peterj.feed.data

import com.peterj.feed.data.model.Products

interface FeedService {
    suspend fun getFeeds(limit: Int, skip: Int): Products
}