package com.github.russiancoder

import org.junit.Assert
import org.junit.jupiter.api.Test


class AppTest {
    @Test
    fun testAppHasAGreeting() {
        val classUnderTest = App()
        Assert.assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}
