import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.russian-coder.gradle-kotlin-webservice"
version = project.properties["application.version"] as String

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    `maven-publish`
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")

    group = project.rootProject.group
    version = project.rootProject.version

    repositories {
        jcenter()
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        implementation(platform(kotlin("bom")))
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        testImplementation(kotlin("test"))
        testImplementation(kotlin("test-junit"))

        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.2")
        testImplementation("org.assertj:assertj-core:3.13.2")
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            suppressWarnings = true
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
