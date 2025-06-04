plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    id("application")
}

group = "dev.OskarJohansson"
version = "unspecified"


repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.modelcontetxprotocol)
    implementation(libs.slf4j.simple)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}