plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Analyzer"
include("server", "core", "crud", "commons", "mcp", "integration")

dependencyResolutionManagement {

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
include("Cache")
include("kafka")