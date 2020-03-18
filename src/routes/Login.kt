package com.nikhil.routes

import com.nikhil.models.request.LoginRequestPayload
import com.nikhil.models.request.LoginRequestPayload.Companion.dbField_password
import com.nikhil.models.request.LoginRequestPayload.Companion.dbField_username
import com.nikhil.utils.exceptions.MissingFieldsException
import com.nikhil.utils.respondSuccess
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.routing.Route
import io.ktor.routing.post

object Login {
    fun Route.login() {

        post("/login") {
            val loginPayload = call.receive<LoginRequestPayload>()
            when {
                loginPayload.username == null -> throw MissingFieldsException(dbField_username)
                loginPayload.password == null -> throw MissingFieldsException(dbField_password)
                else -> call.respondSuccess("Login Success!")
            }
        }
    }
}
