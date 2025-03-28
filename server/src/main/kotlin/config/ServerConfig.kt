package config

import controllers.analyzeRoutes
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*

import kotlinx.serialization.json.Json

fun Application.module() {
    val env = environment.config.propertyOrNull("ktor.environment")?.getString() ?: "dev"
    configureCors(env)
    routing {
        analyzeRoutes()
    }

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            }
        )
    }
}

fun Application.configureCors(env: String) {
    install(CORS) {
        when(env){
            "dev" -> {
                anyHost()
            }
            "prod" -> {
                TODO("add production hosts")
            }
        }
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("MyCustomHeader")
    }
}
