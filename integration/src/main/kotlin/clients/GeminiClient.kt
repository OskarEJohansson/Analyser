package clients

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.*
import io.ktor.http.headers
import models.request.AnalyzeRequest
import models.request.GeminiRequest
import models.response.GeminiResponse
import secrets.Secrets


class GeminiClient(private val client: HttpClient, private val secrets: Secrets) {

    suspend fun analyze(request: AnalyzeRequest): String {
        val request = GeminiRequest.create(request.data)
        val response = client.post("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent") {
            url {
                contentType(ContentType.Application.Json)
                parameters.append("key", secrets.GEMINI_API_KEY)
            }
            headers {
                append(HttpHeaders.Accept, ContentType.Application.Json.toString())
            }
            setBody(request)

        }.body<GeminiResponse>()
        return response.getFirstResponseText() ?: "No response from Gemini"
    }


}
