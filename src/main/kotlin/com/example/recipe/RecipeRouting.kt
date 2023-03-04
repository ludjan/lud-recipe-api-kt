package com.example.recipe

import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import com.example.Provider
import com.example.recipe.model.FacadeRecipe
import com.example.recipe.model.Recipe
import com.example.utils.HttpUtils
import com.example.utils.StringUtils
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import javax.security.sasl.AuthenticationException

val recipeService = Provider.recipeService
val authenticationService = Provider.authenticationService

fun Route.getRecipeRoute() =
    get("/recipe/{recipeId}") {
        HttpUtils.getRequiredParamOrFail(
            call,
            "recipeId",
            StringUtils::stringToLong
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

fun Route.createRecipe() =
    post("/recipe") {

        HttpUtils.getBearerTokenOrFail(call).fold({
            call.respond(status = HttpStatusCode.BadRequest, "Poorly formatted bearer token")
        }, { token ->
            if (!authenticationService.validateToken(token)) {
                println("Rejected POST due to invalid token")
                call.respond(status = HttpStatusCode.Forbidden, "Forbidden")
            }

            try {
                val body = call.receive<String>()
                val facadeRecipe = jacksonObjectMapper().readValue(body, FacadeRecipe::class.java)

                println("Successfully created facade recipe")

                recipeService.createRecipe(facadeRecipe).fold({
                    call.respond(status = HttpStatusCode.BadRequest, "That was a bad request")
                }, { recipe ->
                    call.respond(recipe)
                })
            } catch (e: UnrecognizedPropertyException) {
                call.respond(status = HttpStatusCode.BadRequest, e.message.toString())
            }
        })
    }
