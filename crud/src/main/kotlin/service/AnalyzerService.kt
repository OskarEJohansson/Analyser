package service



import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import model.request.AnalyzeRequest
import model.response.GeminiResponse
import model.secrets.Secrets
import network.KtorClientProvider

class AnalyzerService() {

     suspend fun analyzeWithOpenIA(request: AnalyzeRequest) {
        val response = KtorClientProvider.client.post("https://api.openai.com/v1/chat/completions"){
            headers {
                contentType(ContentType.Application.Json)
                append(HttpHeaders.Accept, ContentType.Application.Json.toString())
                append(HttpHeaders.Authorization, ("TODO"))
            }
            setBody(
                buildJsonObject {
                    put("model", "gpt-4o-mini-2024-07-18")
                    putJsonArray("messages") {
                        addJsonObject {
                            put("role", "user")
                            put("content", "Tell me a programming joke.")
                        }
                    }
                })
        }
        println(response)
    }

    suspend fun analyzeWithGemini2Lite(request: AnalyzeRequest, secrets: Secrets): String {
        val response = KtorClientProvider.client.post("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent") {
                url {
                    contentType(ContentType.Application.Json)
                    parameters.append("key", secrets.GEMINI_API_KEY)
                }
                headers {
                    append(HttpHeaders.Accept, ContentType.Application.Json.toString())
                }
                setBody(
                    buildJsonObject {
                        putJsonArray("contents") {
                            addJsonObject {
                                putJsonArray("parts") {
                                    addJsonObject {
                                        put("text", request.data)
                                    }
                                }
                            }
                        }
                    })
            }.body<GeminiResponse>()
        return response.candidates[0].content.parts[0].text
    }
}