package com.nikhil.models.request

import org.bson.Document

data class RegisterRequestPayload(
    val username: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?
) {
    fun toDocument(): Document {
        return Document(mutableMapOf<String, String>().apply {
            put("username", username ?: "")
            put("password", password ?: "")
            put("firstName", firstName ?: "")
            put("lastName", lastName ?: "")
        }.toMap())
    }
}