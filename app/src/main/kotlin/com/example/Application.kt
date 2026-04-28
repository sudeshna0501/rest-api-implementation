package com.example

import com.example.plugins.configureRouting
import com.example.plugins.configureAuthentication
import com.example.database.DatabaseFactory
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureSerialization()
        configureAuthentication()
        configureRouting()
    }.start(wait = true)
}


fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureAuthentication()
    configureRouting()
}