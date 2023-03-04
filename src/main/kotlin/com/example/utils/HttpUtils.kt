package com.example.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.ktor.server.application.*
import io.ktor.server.plugins.*

object HttpUtils {
    fun <T : Any> getRequiredParamOrFail(
        call: ApplicationCall,
        param: String,
        converter: (input: String) -> Either<Exception, T>
    ): Either<Exception, T> {
        val value = call.parameters["recipeId"] ?: return BadRequestException("You must specify parameter $param")
            .also { println("Bad request exception was thrown")}
            .left()
        return converter(value)
    }
    fun stringToLong(s: String): Either<NumberFormatException, Long> =
        try {
            s.toLong().right()
        } catch (e: NumberFormatException){
            println("Failed to parse string $s to Long")
            e.left()
    }
}