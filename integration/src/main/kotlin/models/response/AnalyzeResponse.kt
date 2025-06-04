package models.response

import kotlinx.serialization.Serializable

@Serializable
data class AnalyzeResponse(private val response: GeminiResponse)

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>
) {
    @Serializable
    data class Candidate(
        val content: Content,
        val finishReason: String,
        val avgLogprobs: Double
    )

    @Serializable
    data class Content(
        val parts: List<Part>,
        val role: String
    )

    @Serializable
    data class Part(
        val text: String
    )

    fun getFirstResponseText(): String? = candidates
        .firstOrNull()
        ?.content
        ?.parts
        ?.firstOrNull()
        ?.text
}



