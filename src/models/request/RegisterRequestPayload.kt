package com.nikhil.models.request

import org.bson.Document

data class RegisterRequestPayload(
    val username: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?
) {

    companion object {
        const val dbField_username = "username"
        const val dbField_password = "password"
        const val dbField_firstName = "firstName"
        const val dbField_lastName = "lastName"
    }

    fun toDocument(): Document {
        return Document(mutableMapOf<String, String>().apply {
            put(dbField_username, username ?: "")
            put(dbField_password, password ?: "")
            put(dbField_firstName, firstName ?: "")
            put(dbField_lastName, lastName ?: "")
        }.toMap())
    }
}