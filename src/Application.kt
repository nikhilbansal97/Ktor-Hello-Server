package com.nikhil

import com.nikhil.database.DatabaseManager
import com.nikhil.features.SessionFeature.installSessionFeature
import com.nikhil.features.StatusPagesFeature.installStatusPagesFeature
import com.nikhil.routes.Login.login
import com.nikhil.routes.Logout.logout
import com.nikhil.routes.Register.register
import com.nikhil.routes.Users.users
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

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
    // Maintain sessions
    installSessionFeature()

    val databaseManager = DatabaseManager().apply { init() }

    routing {
        login()
        logout()
        register(databaseManager)

        users(databaseManager)
    }
}