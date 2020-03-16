package com.nikhil

import com.nikhil.routes.login
import com.ryanharter.ktor.moshi.moshi
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    // Log all the calls.
    install(CallLogging)
    // Used to parse the request and response as objects.
    install(ContentNegotiation) {
        moshi()
    }
    // Used to handle the error in the server.
    install(StatusPages) {
        exception<Throwable> { exception ->
            call.respondText(exception.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
        }
    }

    // Configure Server.
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::serverModule
    ).apply { start(wait = true) }
}

fun Application.serverModule() {
    routing {
        login()
    }
}