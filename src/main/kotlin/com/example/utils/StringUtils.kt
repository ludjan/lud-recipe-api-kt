package com.example.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.util.InputMismatchException

object StringUtils {
    fun getRestOfString(originalString: String, expectedStart: String): Either<InputMismatchException, String> {
        if (originalString.length < expectedStart.length) InputMismatchException("Original string '$originalString' is shorter than expected start '$expectedStart'").left()
        if (!originalString.contains(expectedStart)) InputMismatchException("Original string '$originalString' does not contain expected start '$expectedStart'").left()
        val prefix = originalString.substring(0, expectedStart.length)
        if (prefix != expectedStart) InputMismatchException("Original string '$originalString' does not start with '$expectedStart'").left()
        return originalString.substring(expectedStart.length, originalString.length).right()
    }

    fun stringToLong(s: String): Either<NumberFormatException, Long> =
        try {
            s.toLong().right()
        } catch (e: NumberFormatException){
            println("Failed to parse string $s to Long")
            e.left()
        }
}