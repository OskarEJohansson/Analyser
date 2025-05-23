package controllers

import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import model.request.AnalyzeRequest
import model.response.AnalyzeResponse
import model.secrets.Secrets
import service.AnalyzerService


fun Route.analyzeRoutes(secrets: Secrets) {
    post("/api/v1/analyze") {
        val input = call.receive<AnalyzeRequest>()
        val result = AnalyzerService().analyzeWithGemini2Lite(input, secrets)
        call.respond(
            result,
            typeInfo = TypeInfo(AnalyzeResponse::class)
        )
    }
}