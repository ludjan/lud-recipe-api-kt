package com.example

class AuthenticationService {

    private val tokenPrefix = "Bearer "
    private val secretToken = "not-a-secret"
    private val completeToken = tokenPrefix + secretToken

    fun validateToken(token: String) = token == completeToken

}