package com.example

import com.example.plugins.configureSerialization
import com.example.recipe.exampleRecipe1
import com.example.recipe.model.FacadeRecipe
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class RecipeIntegrationSpec {

    @Test
    fun testGetRecipe() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }
        client.get("/recipe/1").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/recipe/999").apply {
            assertEquals(HttpStatusCode.NotFound, status)
        }
    }

    @Test
    fun testPostRecipe() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }
        val recipe = exampleRecipe1()
        val facadeRecipe = FacadeRecipe(recipe.title, recipe.description, recipe.portions, recipe.ingredientQuantityUnits, recipe.steps)
        val serialized = jacksonObjectMapper().writeValueAsString(facadeRecipe)

        val response = client.post("/recipe") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer not-a-secret")
            setBody(serialized)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

}