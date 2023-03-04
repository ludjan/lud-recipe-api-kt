package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.recipe.model.Recipe
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult

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
    install(RequestValidation) {
        validate<Recipe> { recipe ->
            if (recipe.id <= 0) ValidationResult.Invalid("A recipe cannot have id 0")
            else ValidationResult.Valid
        }
    }
    configureSerialization()
    configureHTTP()
    configureSecurity()
    configureRouting()
}
