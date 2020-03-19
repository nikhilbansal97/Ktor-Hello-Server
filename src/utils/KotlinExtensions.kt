package com.nikhil.utils

import com.mongodb.client.MongoCollection
import com.nikhil.database.models.User
import com.nikhil.models.response.ErrorResponse
import com.nikhil.models.response.SuccessResponse
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import org.bson.Document

suspend fun ApplicationCall.respondSuccess(message: Any) {
    respond(HttpStatusCode.OK, message)
}

suspend fun ApplicationCall.respondSuccess(message: String) {
    respond(HttpStatusCode.OK, SuccessResponse(message))
}

suspend fun ApplicationCall.respondBadRequest(message: String) {
    respond(HttpStatusCode.BadRequest, ErrorResponse(message))
}

fun Int?.isNotNullOrZero(): Boolean {
    return this != null && this != 0
}

fun MongoCollection<Document>.toUsersList(): List<User> {
    return find().map { User.fromDocument(it) }.toList()
}