package model.secrets

import kotlinx.serialization.Serializable

@Serializable
    data class Secrets(
        val GEMINI_API_KEY: String,
        val GEMINI_PROJECT: String
    )