package com.example1.twitterapp.model

import com.google.gson.annotations.SerializedName

data class User(
        val utcOffset: Any? = null,
        val friendsCount: Int? = null,
        val profileImageUrlHttps: String? = null,
        val listedCount: Int? = null,

        @SerializedName("profile_background_image_url")
        var profileBackgroundImageUrl: String? = null,

        val defaultProfileImage: Boolean? = null,
        val favouritesCount: Int? = null,
        val description: String? = null,
        val createdAt: String? = null,
        val isTranslator: Boolean? = null,
        val profileBackgroundImageUrlHttps: String? = null,
        val jsonMemberProtected: Boolean? = null,
        val screenName: String? = null,
        val idStr: String? = null,
        val profileLinkColor: String? = null,
        val isTranslationEnabled: Boolean? = null,
        val translatorType: String? = null,
        val id: Long? = null,
        val geoEnabled: Boolean? = null,
        val profileBackgroundColor: String? = null,
        val lang: String? = null,
        val hasExtendedProfile: Boolean? = null,
        val profileSidebarBorderColor: String? = null,
        val profileTextColor: String? = null,
        val verified: Boolean? = null,
        @SerializedName("profile_image_url")
        var profileImageUrl: String? = null,
        val timeZone: Any? = null,
        val url: Any? = null,
        val contributorsEnabled: Boolean? = null,
        val profileBackgroundTile: Boolean? = null,
        @SerializedName("profile_banner_url")
        val profileBannerUrl: String? = null,
        val entities: Entities? = null,
        val statusesCount: Int? = null,
        val followRequestSent: Boolean? = null,
        val followersCount: Int? = null,
        val profileUseBackgroundImage: Boolean? = null,
        val defaultProfile: Boolean? = null,
        val following: Boolean? = null,
        var name: String? = null,
        val location: String? = null,
        val profileSidebarFillColor: String? = null,
        val notifications: Boolean? = null
)
