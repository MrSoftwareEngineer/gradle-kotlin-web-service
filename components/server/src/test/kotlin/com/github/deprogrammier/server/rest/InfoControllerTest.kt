package com.github.deprogrammier.server.rest

import com.github.deprogrammier.rest.common.ServerInfo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class InfoControllerTest : BaseControllerTest() {
    @Test
    fun testGetServerInfo() {
        val mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/info/server"))
                .andReturn()
        val actualServerInfo = mvcResult.getObject(ServerInfo::class.java)
        Assertions.assertEquals("1.0-SNAPSHOT", actualServerInfo.version)
    }
}
