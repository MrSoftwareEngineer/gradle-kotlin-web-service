package com.github.deprogrammier.rest.common

import java.util.*

data class ServerInfo(val version: String, val buildDate: Date, val versionsApi: Array<String> = arrayOf("1")) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ServerInfo

        if (version != other.version) return false
        if (buildDate != other.buildDate) return false
        if (!versionsApi.contentEquals(other.versionsApi)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = version.hashCode()
        result = 31 * result + buildDate.hashCode()
        result = 31 * result + versionsApi.contentHashCode()
        return result
    }

}
