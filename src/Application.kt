package com.nikhil

import com.nikhil.database.DatabaseManager
import com.nikhil.routes.Login.login
import com.nikhil.routes.Register.register
import com.nikhil.routes.Users.users
import com.nikhil.utils.exceptions.MissingFieldsException
import com.nikhil.utils.exceptions.ServerException
import com.nikhil.utils.respondBadRequest
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.error

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // Configure Server.
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::serverModule,
        watchPaths = listOf("/Hello")
    ).apply { start(wait = true) }
}

fun Application.serverModule() {

    // Log all the calls.
    install(CallLogging)
    // Used to parse the request and response as objects.
    install(ContentNegotiation) {
        gson()
    }
    // Used to handle the error in the server.
    installStatusPagesFeature()

    val databaseManager = DatabaseManager().apply { init() }

    routing {
        login()
        register(databaseManager)
        users(databaseManager)
    }
}

private fun Application.installStatusPagesFeature() {
    install(StatusPages) {
        exception<MissingFieldsException> { exception ->
            call.respondBadRequest(exception.localizedMessage)
        }
        exception<ServerException> { exception -> log.error(exception) }
        exception<Throwable> { exception ->
            call.respondText(
                exception.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError
            )
        }
    }
}