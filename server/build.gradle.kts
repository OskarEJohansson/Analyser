plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

application {
    mainClass.set("MainKt")
}

group = "dev.OskarJohansson"
version = "1.0-SNAPSHOT"


dependencies {
    implementation(project(":crud"))
    implementation(project(":core"))
    implementation(project(":mcp"))
    implementation(project(":integration"))
    implementation(libs.modelcontetxprotocol)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.statuspages)
    implementation(libs.kotlin.test.junit)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.test.host)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}