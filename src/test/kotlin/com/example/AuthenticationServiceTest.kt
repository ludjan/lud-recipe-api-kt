package com.example


import com.example.recipe.authenticationService
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AuthenticationServiceTest {

    @Test
    fun badToken() {
        assertFalse(authenticationService.validateToken("not-secret"))
    }

    @Test
    fun goodToken() {
        assertTrue(authenticationService.validateToken("not-a-secret"))
    }
}