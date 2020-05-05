package com.github.deprogrammier.server.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.deprogrammier.rest.common.ServerInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.util.*

@Configuration
class AppConfig {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Bean
    fun serverInfo(): ServerInfo {
        return try {
            ClassPathResource("server.info")
                    .inputStream
                    .use {
                        objectMapper.readValue(it, ServerInfo::class.java)
                    }
        } catch (e: Exception) {
            ServerInfo("NOT_SET", Date(), emptyArray())
        }
    }
}
