package com.nikhil.routes

import com.nikhil.models.auth.Session
import com.nikhil.models.request.LoginRequestPayload
import com.nikhil.models.request.LoginRequestPayload.Companion.dbField_password
import com.nikhil.models.request.LoginRequestPayload.Companion.dbField_username
import com.nikhil.utils.exceptions.MissingFieldsException
import com.nikhil.utils.respondSuccess
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import io.ktor.util.pipeline.PipelineContext

object Login {
    fun Route.login() {

        post("/login") {
            val loginPayload = call.receive<LoginRequestPayload>()
            when {
                loginPayload.username == null -> throw MissingFieldsException(dbField_username)
                loginPayload.password == null -> throw MissingFieldsException(dbField_password)
                else -> loginSuccess(loginPayload)
            }
        }
    }

    private suspend fun PipelineContext<Unit, ApplicationCall>.loginSuccess(loginPayload: LoginRequestPayload) {
        // Create a new session for the subsequent calls.
        loginPayload.username?.let { username ->
            call.sessions.set(Session(username))
        } ?: kotlin.run {
            println("Unable to set the session as username is null")
        }

        // Send the response
        call.respondSuccess("Login Success!")
    }
}
