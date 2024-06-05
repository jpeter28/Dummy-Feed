package com.peterj.feed.data.network

import com.peterj.feed.data.FeedService
import com.peterj.feed.data.model.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkFeedService : FeedService {

    @GET("products")
    override suspend fun getFeeds(@Query("limit") limit: Int, @Query("skip") skip: Int): Products
}