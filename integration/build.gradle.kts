plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "dev.OskarJohansson"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":commons"))
    implementation(libs.ktor.client.core)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation("io.ktor:ktor-client-core-jvm:3.1.1")
    implementation("io.ktor:ktor-client-apache:3.1.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}