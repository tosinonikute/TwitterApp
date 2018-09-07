package com.example1.twitterapp.model

data class Entities(
        val description: Description? = null,
        val urls: List<Any?>? = null,
        val hashtags: List<Any?>? = null,
        val userMentions: List<Any?>? = null,
        val symbols: List<Any?>? = null
)
