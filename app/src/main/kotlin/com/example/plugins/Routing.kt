package com.example.plugins

import com.example.repository.UserRepository
import com.example.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.routes.authRoutes
import io.ktor.server.auth.authenticate

fun Application.configureRouting() {
    val userRepository = UserRepository()

    routing {
        get("/") {
            call.respondText("Kotlin REST API is running")
        }

        authRoutes(userRepository)

        authenticate {
            userRoutes(userRepository)
        }
    }
}