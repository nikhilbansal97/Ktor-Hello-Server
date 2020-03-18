package com.nikhil.routes

import com.mongodb.client.model.Filters.eq
import com.nikhil.database.DatabaseManager
import com.nikhil.models.request.RegisterRequestPayload
import com.nikhil.utils.exceptions.MissingFieldsException
import com.nikhil.utils.isNotNullOrZero
import com.nikhil.utils.respondBadRequest
import com.nikhil.utils.respondSuccess
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.util.pipeline.PipelineContext

object Register {

    private lateinit var databaseManager: DatabaseManager

    fun Route.register(dbManager: DatabaseManager) {
        databaseManager = dbManager

        post("/register") {
            val registerPayload = call.receive<RegisterRequestPayload>()
            when {
                registerPayload.username == null -> throw MissingFieldsException("username")
                registerPayload.password == null -> throw MissingFieldsException("password")
                registerPayload.firstName == null -> throw MissingFieldsException("firstName")
                registerPayload.lastName == null -> throw MissingFieldsException("lastName")
                else -> registerUser(registerPayload)
            }
        }
    }

    private suspend fun PipelineContext<Unit, ApplicationCall>.registerUser(registerPayload: RegisterRequestPayload) {
        when {
            isExistingUser(registerPayload.username) -> {
                call.respondBadRequest("Username already exists.")
            }
            else -> {
                createNewUser(registerPayload)
                call.respondSuccess("Registration successful for user ${registerPayload.username}!")
            }
        }
    }

    private fun createNewUser(registerPayload: RegisterRequestPayload) {
        databaseManager.getUsers()?.insertOne(registerPayload.toDocument())
    }

    private fun isExistingUser(username: String?): Boolean {
        return username?.let {
            val users = databaseManager.getUsers()
            users?.find(eq("username", it))?.count().isNotNullOrZero()
        } ?: false
    }
}
