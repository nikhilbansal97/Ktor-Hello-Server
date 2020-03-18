package com.nikhil.routes

import com.nikhil.database.DatabaseManager
import com.nikhil.utils.respondSuccess
import com.nikhil.utils.toUsersList
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get

object Users {

    private lateinit var databaseManager: DatabaseManager

    fun Route.users(dbManager: DatabaseManager) {
        databaseManager = dbManager

        get("/users") {
            val users = databaseManager.getUsers()
            users?.let { data ->
                call.respondSuccess(data.toUsersList())
            }
        }
    }
}