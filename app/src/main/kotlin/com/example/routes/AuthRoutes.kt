package com.example.routes

import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.auth.JwtConfig
import com.example.models.*
import com.example.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.authRoutes(repository: UserRepository) {

    route("/auth") {

        post("/register") {
            val request = call.receive<RegisterRequest>()

            val hashed = BCrypt.withDefaults()
                .hashToString(12, request.password.toCharArray())

            val user = repository.createUser(
                request.name,
                request.email,
                hashed
            )

            val token = JwtConfig.generateToken(user.id, user.email)

            call.respond(AuthResponse(token))
        }

        post("/login") {
            val request = call.receive<LoginRequest>()

            val user = repository.findByEmail(request.email)

            if (user == null) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
                return@post
            }

            val result = BCrypt.verifyer().verify(
                request.password.toCharArray(),
                user.passwordHash
            )

            if (!result.verified) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
                return@post
            }

            val token = JwtConfig.generateToken(user.id, user.email)

            call.respond(AuthResponse(token))
        }
    }
}