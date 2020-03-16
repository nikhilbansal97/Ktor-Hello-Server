package com.nikhil.routes

import com.nikhil.models.LoginRequestPayload
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.login() {

    post("/login") {
        val loginPayload = call.receive<LoginRequestPayload>()
        call.respondText(loginPayload.username)
    }
}