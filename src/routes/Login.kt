package com.nikhil.routes

import com.nikhil.models.request.LoginRequestPayload
import com.nikhil.models.response.SuccessResponse
import com.nikhil.utils.exceptions.MissingFieldsException
import com.nikhil.utils.respondSuccess
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.login() {

    post("/login") {
        val loginPayload = call.receive<LoginRequestPayload>()
        when {
            loginPayload.username == null -> {
                throw MissingFieldsException("username")
            }
            loginPayload.password == null -> {
                throw MissingFieldsException("password")
            }
            else -> call.respondSuccess(SuccessResponse("Your username is ${loginPayload.username} and password is ${loginPayload.password}"))
        }
    }
}