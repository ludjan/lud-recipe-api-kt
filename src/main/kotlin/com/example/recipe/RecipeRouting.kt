package com.example.recipe

import com.example.Provider
import com.example.utils.HttpUtils
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

val recipeService = Provider.recipeService

fun Route.getRecipeRoute() =
    get("/recipe/{recipeId}") {
        HttpUtils.getRequiredParamOrFail(
            call,
            "recipeId",
            HttpUtils::stringToLong
        ).fold({
            call.respond(status = HttpStatusCode.BadRequest, "That was a bad request")
        },
            { recipeId ->
                recipeService.getRecipeById(recipeId).fold(
                    {
                        call.respond(status = HttpStatusCode.NotFound, "Recipe not found")
                    },
                    { recipe ->
                        call.respond(status = HttpStatusCode.OK, recipe)
                    })
            })
    }

fun Route.getOddRecipes() =
    get("/odd-recipes") {
        recipeService.getOddRecipes().fold({
            call.respond(status = HttpStatusCode.NotFound, "No recipes were found")
        },
            { recipes ->
                call.respond(status = HttpStatusCode.OK, recipes)
            })
    }

fun Route.getEvenRecipes() =
    get("/even-recipes") {
        call.respond(
            status = HttpStatusCode.OK,
            message = recipeService.getEvenRecipes()
        )
    }
