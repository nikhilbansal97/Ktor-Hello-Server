package com.nikhil.utils

import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText

suspend fun ApplicationCall.respondJson(message: String) {
    respondText(message, ContentType.Application.Json)
}

suspend fun ApplicationCall.respondSuccess(message: Any) {
    respond(HttpStatusCode.OK, message)
}

suspend fun ApplicationCall.respondBadRequest(message: Any) {
    respond(HttpStatusCode.BadRequest, message)
}