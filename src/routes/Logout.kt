package com.nikhil.routes

import com.nikhil.models.auth.Session
import com.nikhil.utils.respondSuccess
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.sessions.clear
import io.ktor.sessions.sessions

object Logout {

    fun Route.logout() {
        post("/logout") {
            call.sessions.clear<Session>()
            call.respondSuccess("Logout Success!")
        }
    }
}