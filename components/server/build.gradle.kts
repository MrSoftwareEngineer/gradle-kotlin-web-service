import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

buildscript {
    dependencies {
        classpath("com.fasterxml.jackson.core:jackson-databind:2.11.0")
    }
}

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
    id("com.bmuschko.docker-spring-boot-application") version "6.4.0"
}

dependencies {
    implementation(project(":components:common"))
    implementation(platform("org.springframework.boot:spring-boot-starter-parent:2.2.2.RELEASE"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage", "junit-vintage-engine")
    }
}

allOpen {
    annotation("org.springframework.context.annotation.Configuration")
}

docker {
    springBootApplication {
        baseImage.set("openjdk")
        ports.set(listOf(9090, 8080))
        images.set(setOf("${rootProject.properties["docker.registry"]}/${project.name}:${project.version}"))
        jvmArgs.set(listOf("-Dspring.profiles.active=production", "-Xmx2048m"))
    }
}

val serverInfo by lazy { file("src/main/resources/server.info") }

val setBuildDataTask = tasks.create("setBuildData") {
    val mapper = ObjectMapper()
    mapper.writeValue(
            serverInfo,
            mapOf("version" to version,
                    "buildDate" to Date())
    )
}

tasks.named("clean").configure() {
    doLast {
        serverInfo.delete()
    }
}

tasks.withType<com.bmuschko.gradle.docker.tasks.DockerOperation> {
    dependsOn("build")
}

tasks.named("processResources") {
    dependsOn(setBuildDataTask)
}
