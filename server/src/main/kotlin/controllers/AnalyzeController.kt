package controllers

import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import model.request.AnalyzeRequest
import model.response.AnalyzeResponse

import service.AnalyzerService.analyze

fun Route.analyzeRoutes() {
    post("/api/v1/analyze") {
        println("inside Analyze")
        val input = call.receive<AnalyzeRequest>()
        val result = analyze(input)
        call.respond(
            result,
            typeInfo = TypeInfo(AnalyzeResponse::class)
        )
    }
}