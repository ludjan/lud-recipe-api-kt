package com.example

import com.example.plugins.configureSerialization
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
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
}