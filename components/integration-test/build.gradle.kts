plugins {
    id("com.bmuschko.docker-remote-api") version "6.4.0"
}

dependencies {
    testImplementation(project(":components:client"))
    testImplementation(project(":components:common"))
    testImplementation("org.slf4j:slf4j-simple:1.7.30")
    testImplementation("org.slf4j:slf4j-api:1.7.30")
}

val createMyAppContainer by tasks.creating(com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer::class) {
    targetImageId("${rootProject.properties["docker.registry"]}/server:${project.version}")
    hostConfig.portBindings.set(listOf("8080:8080"))
    hostConfig.autoRemove.set(true)
}

val startMyAppContainer by tasks.creating(com.bmuschko.gradle.docker.tasks.container.DockerStartContainer::class) {
    dependsOn(createMyAppContainer)
    targetContainerId(createMyAppContainer.containerId)
}

val stopMyAppContainer by tasks.creating(com.bmuschko.gradle.docker.tasks.container.DockerStopContainer::class) {
    targetContainerId(createMyAppContainer.containerId)
}

tasks {
    withType<Test> {
        dependsOn(startMyAppContainer)
        finalizedBy(stopMyAppContainer)
    }
}
