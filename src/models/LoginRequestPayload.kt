package com.nikhil.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestPayload(
    val username: String,
    val password: String
)