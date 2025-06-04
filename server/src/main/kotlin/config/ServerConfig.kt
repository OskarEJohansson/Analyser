package config

import config.IntegrationModule.provideGeminiClient
import config.IntegrationModule.provideMCPClient
import controllers.analyzeRoutes
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

fun Application.module() {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
    }

    install(StatusPages) {
        exception<BadRequestException> { call, cause ->
            call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
        }
        exception<NotFoundException> { call, cause ->
            call.respondText(text = "404: ${cause.message}", status = HttpStatusCode.NotFound)
        }
        exception<UnsupportedMediaTypeException> { call, cause ->
            call.respondText(text = "415: ${cause.message}", status = HttpStatusCode.UnsupportedMediaType)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    install(CallLogging){
        level = Level.INFO
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Status: $status, HTTP method: $httpMethod, User agent: $userAgent"
        }
    }

    val mcpClient = provideMCPClient()
    val geminiClient = provideGeminiClient()


    routing {
        analyzeRoutes(geminiClient)
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
        parseAndSortContentTypeHeader("application/json")
        allowHeader("MyCustomHeader")
    }
}
