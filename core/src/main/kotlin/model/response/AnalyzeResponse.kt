package model.response

import kotlinx.serialization.Serializable

@Serializable
data class AnalyzeResponse(private val data: String) {
}