package model.request

import kotlinx.serialization.Serializable

@Serializable
data class AnalyzeRequest(val data: String) {
}