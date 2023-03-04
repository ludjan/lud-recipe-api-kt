package com.example.utils

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import com.example.recipe.authenticationService
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import javax.security.sasl.AuthenticationException

object HttpUtils {
    fun <T : Any> getRequiredParamOrFail(
        call: ApplicationCall,
        param: String,
        converter: (input: String) -> Either<Exception, T>
    ): Either<Exception, T> {
        val value = call.parameters["recipeId"] ?: return BadRequestException("You must specify parameter $param")
            .also { println("Bad request exception was thrown") }.left()
        return converter(value)
    }

    fun getBearerTokenOrFail(call: ApplicationCall): Either<Exception, String> =
        call.request.headers["Authorization"]?.right() ?: AuthenticationException().left()
}