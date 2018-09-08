package com.example1.twitterapp.model

class Tweets(
        val inReplyToStatusIdStr: Any? = null,
        val inReplyToStatusId: Any? = null,
        val coordinates: Any? = null,
        val createdAt: String? = null,
        val truncated: Boolean? = null,
        val inReplyToUserIdStr: Any? = null,
        val source: String? = null,
        val retweetCount: Int? = null,
        val retweeted: Boolean? = null,
        val geo: Any? = null,
        val inReplyToScreenName: Any? = null,
        val isQuoteStatus: Boolean? = null,
        val entities: Entities? = null,
        val idStr: String? = null,
        val inReplyToUserId: Any? = null,
        val favoriteCount: Int? = null,
        val id: Long? = null,
        val text: String? = null,
        val place: Any? = null,
        val contributors: Any? = null,
        val lang: String? = null,
        var user: User? = null,
        val favorited: Boolean? = null
)
