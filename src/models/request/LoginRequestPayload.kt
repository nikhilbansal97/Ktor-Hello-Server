package com.nikhil.models.request

data class LoginRequestPayload(
    val username: String?,
    val password: String?
) {
    companion object {
        const val dbField_username = "username"
        const val dbField_password = "password"
    }
}