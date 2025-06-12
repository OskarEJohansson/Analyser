plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "dev.OskarJohansson"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":commons"))
    implementation(project(":core"))
    implementation(libs.apache.kafka.client)
    implementation(libs.slf4j.api)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}