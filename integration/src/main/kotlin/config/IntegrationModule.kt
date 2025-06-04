package config

import clients.GeminiClient
import network.KtorClientProvider
import secrets.SecretsConfig

object IntegrationModule {
    fun provideGeminiClient(): GeminiClient = GeminiClient(KtorClientProvider.client , SecretsConfig.SecretsLoader("server/src/main/resources/secrets.json").init())
}