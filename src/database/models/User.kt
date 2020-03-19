package com.nikhil.database.models

import org.bson.Document


data class User(
    val id: String,
    val username: String,
    val firstName: String,
    val lastName: String
) {
    companion object {

        const val dbField_id = "_id"
        const val dbField_username = "username"
        const val dbField_firstName = "firstName"
        const val dbField_lastName = "lastName"

        fun fromDocument(document: Document): User =
            User(
                id = document.getObjectId(dbField_id).toHexString(),
                username = document.getString(dbField_username),
                firstName = document.getString(dbField_firstName),
                lastName = document.getString(dbField_lastName)
            )
    }
}