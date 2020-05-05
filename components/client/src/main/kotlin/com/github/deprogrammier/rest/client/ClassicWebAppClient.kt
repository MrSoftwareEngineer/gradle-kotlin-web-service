package com.github.deprogrammier.rest.client

import com.fasterxml.jackson.module.kotlin.KotlinModule
import feign.Feign
import feign.Logger
import feign.httpclient.ApacheHttpClient
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.jaxrs.JAXRSContract
import feign.slf4j.Slf4jLogger


class ClassicWebAppClient(apiUrl: String) : WebAppClient {
    private val webAppClient: WebAppClient = Feign.builder()
            .client(ApacheHttpClient())
            .contract(JAXRSContract())
            .encoder(JacksonEncoder(listOf(KotlinModule())))
            .decoder(JacksonDecoder(listOf(KotlinModule())))
            .logger(Slf4jLogger(WebAppClient::class.java))
            .logLevel(Logger.Level.FULL)
            .target(WebAppClient::class.java, apiUrl)

    override fun serverInfo() = webAppClient.serverInfo()
}
