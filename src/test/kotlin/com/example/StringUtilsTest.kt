package com.example

import arrow.core.getOrElse
import com.example.utils.StringUtils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StringUtilsTest {

    @Test
    fun getRestOfStringTest() {
        val prefix = "Authorization: "
        val suffix = "not-a-secret"
        val prefixAndSuffix = prefix + suffix

        val result = StringUtils.getRestOfString(prefixAndSuffix, prefix)
        assertTrue(result.isRight())

        val stringResult = result.getOrElse { AssertionError() }
        assertEquals(suffix, stringResult)
    }
}