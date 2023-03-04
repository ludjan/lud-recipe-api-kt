package com.example

import com.example.recipe.createRecipe
import com.example.recipe.getEvenRecipes
import com.example.recipe.getOddRecipes
import com.example.recipe.getRecipeRoute
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    routing {
        status()
        getEvenRecipes()
        getOddRecipes()
        getRecipeRoute()
        createRecipe()
    }
}

fun Route.status() =
    get("/") {
        call.respond(status = HttpStatusCode.OK, "Application is running")
    }

