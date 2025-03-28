plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "dev.OskarJohansson"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)
    implementation(project(":core"))
    implementation(project(":commons"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}