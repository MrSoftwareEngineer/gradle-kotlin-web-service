package com.github.deprogrammier.test

import com.github.deprogrammier.rest.client.ClassicWebAppClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class InfoControllerTest {
    @Test
    fun testServerInfo() {
        val serverInfo = ClassicWebAppClient("http://localhost:8080").serverInfo()
        Assertions.assertEquals("1.0-SNAPSHOT", serverInfo.version, "ServerInfo.version is wrong")
        Assertions.assertArrayEquals(arrayOf("1"), serverInfo.versionsApi, "ServerInfo.versionApi is wrong")
    }
}
