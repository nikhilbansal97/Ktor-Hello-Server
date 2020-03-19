package com.nikhil.features

import com.nikhil.models.auth.Session
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.directorySessionStorage
import java.io.File

object SessionFeature {

    private const val SESSION_COOKIE_NAME = "COOKIE_SESSION_ID"

    fun Application.installSessionFeature() {
        install(Sessions) {
            cookie<Session>(
                name = SESSION_COOKIE_NAME,
                storage = directorySessionStorage(File(".sessions"), cached = true)
            ) {
                cookie.path = "/"
            }
        }
    }
}