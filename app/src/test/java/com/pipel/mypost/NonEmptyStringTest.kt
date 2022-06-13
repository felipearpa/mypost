package com.pipel.mypost

import com.pipel.mypost.type.NonEmptyString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NonEmptyStringTest {

    @Test
    fun `given a not empty string when a NonEmptyString is created then a NonEmptyString than contains the string value is returned`() {
        val sourceRaw = "not empty string"
        val nonEmptyString = NonEmptyString(sourceRaw)
        Assertions.assertEquals(sourceRaw, nonEmptyString.value)
    }

    @Test
    fun `given an empty string when a NonEmptyString is created then an exception is raised`() {
        val sourceRaw = ""
        Assertions.assertThrows(
            IllegalArgumentException::class.java
        ) { NonEmptyString(sourceRaw) }
    }

}