package com.github.deprogrammier.rest.client

import com.github.deprogrammier.rest.common.ServerInfo
import javax.ws.rs.GET
import javax.ws.rs.Path


interface WebAppClient {
    @GET
    @Path("/info/server")
    fun serverInfo(): ServerInfo
}
