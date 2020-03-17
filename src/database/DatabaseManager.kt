package com.nikhil.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.nikhil.utils.exceptions.ServerException
import org.bson.Document

class DatabaseManager {

    companion object {
        private const val ENV_VAR_DB_USERNAME = "KTOR_HELLO_WORLD_DB_USERNAME"
        private const val ENV_VAR_DB_PASSWORD = "KTOR_HELLO_WORLD_DB_PASSWORD"
    }

    private var mongoClient: MongoClient? = null

    private var usersDatabase: MongoDatabase? = null

    fun init() {
        val (username, password) = getUsernameAndPassword()
        val mongoClientUri =
            MongoClientURI("mongodb+srv://$username:$password@ktor-hello-world-db-cluster-kj5gk.mongodb.net/test?retryWrites=true&w=majority")
        mongoClient = MongoClient(mongoClientUri)
        usersDatabase = mongoClient?.getDatabase("ktor_hello_world_db")
    }

    fun getUsers(): MongoCollection<Document>? = usersDatabase?.getCollection("users")

    fun getBlogs(): MongoCollection<Document>? = usersDatabase?.getCollection("blogs")

    private fun getUsernameAndPassword(): Pair<String, String> {
        val username = System.getenv(ENV_VAR_DB_USERNAME)
        val password = System.getenv(ENV_VAR_DB_PASSWORD)
        return when {
            username == null -> throw ServerException("$ENV_VAR_DB_USERNAME environment variable not set.")
            password == null -> throw ServerException("$ENV_VAR_DB_PASSWORD environment variable not set.")
            else -> Pair(username, password)
        }
    }
}