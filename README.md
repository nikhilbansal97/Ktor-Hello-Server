# Ktor Hello Server

Simple project to showcase how to use some basic features and define routes. It is hooked to a MongoDb Database to save the users information.

## Features uses
1. [Call Logging](https://ktor.io/servers/features/call-logging.html)
2. [Content Negotiation](https://ktor.io/servers/features/content-negotiation.html)
3. [Status Pages](https://ktor.io/servers/features/status-pages.html)
4. [Sessions](https://ktor.io/servers/features/sessions.html)

## Routes
1. POST `/register`
  
  Request Payload:
  ```
  {
    "username": "john",
    "password": "password",
    "firstName": "John",
    "lastName": "Doe"
  }
  ```
  Response:
  ```
  {
    "message": "Registration successful for user john!"
  }
  ```
  
2. POST `/login`

  Request Payload:
  ```
  {
    "username": "john",
    "password": "password"
  }
  ```
  Response:
  ```
  {
    "message": "Login Success!"
  }
  ```
  
3. GET `/users`

  Response:
  ```
  [
    {
        "id": "5e731f41b7523f2b44eec847",
        "username": "john",
        "firstName": "John",
        "lastName": "Doe"
    }
  ]
  ```
