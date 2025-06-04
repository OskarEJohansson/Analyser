package models.request

import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequest(
    val contents: List<Content>){

    @Serializable
    data class Content(val parts: List<Part>)
    @Serializable
    data class Part( val text: String)

    companion object {
        fun create(data: String) = GeminiRequest(listOf(Content(listOf(Part(data)))))
    }
}