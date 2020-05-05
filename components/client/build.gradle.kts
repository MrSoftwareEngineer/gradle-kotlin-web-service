dependencies {
    implementation(project(":components:common"))
    implementation(platform("io.github.openfeign:feign-bom:11.0"))
    implementation("io.github.openfeign:feign-httpclient")
    implementation("io.github.openfeign:feign-jackson")
    implementation("io.github.openfeign:feign-slf4j")
    implementation("io.github.openfeign:feign-jaxrs")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
}
