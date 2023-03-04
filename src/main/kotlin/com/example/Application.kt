package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {

    val port = findPort()

    embeddedServer(
        Netty,
        port = port,
        host = "0.0.0.0",
        module = Application::module)
        .start(wait = true)
}

fun findPort() =
    when (System.getenv("PORT")) {
        null -> 4444
        else -> System.getenv("PORT").toInt()
    }

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureSecurity()
    configureRouting()
}
