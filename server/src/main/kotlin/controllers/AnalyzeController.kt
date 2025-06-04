package controllers


import clients.GeminiClient
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import models.request.AnalyzeRequest
import models.response.AnalyzeResponse


fun Route.analyzeRoutes(geminiClient: GeminiClient) {
    post("/api/v1/analyze") {
        val input = call.receive<AnalyzeRequest>()
        val result = geminiClient.analyze(input)
        call.respond(
            result,
            typeInfo = TypeInfo(AnalyzeResponse::class)
        )
    }
}