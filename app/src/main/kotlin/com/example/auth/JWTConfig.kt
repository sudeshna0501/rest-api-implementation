package com.example.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

object JwtConfig {
    private const val secret = "super-secret-key"
    private const val issuer = "ktor-api"
    private const val audience = "ktor-audience"

    fun generateToken(userId: Int, email: String): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("userId", userId)
            .withClaim("email", email)
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifier() = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(audience)
        .withIssuer(issuer)
        .build()
}