package com.github.deprogrammier.server.rest

import com.github.deprogrammier.rest.common.ServerInfo
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("info")
class InfoController(
        private val serverInfo: ServerInfo
) {
    @GetMapping("server", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getVersion(): ServerInfo {
        return serverInfo
    }
}
