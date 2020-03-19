package com.nikhil.features

import com.nikhil.utils.exceptions.MissingFieldsException
import com.nikhil.utils.exceptions.ServerException
import com.nikhil.utils.respondBadRequest
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.util.error

object StatusPagesFeature {

    fun Application.installStatusPagesFeature() {
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
}