package config

import clients.GeminiClient
import clients.McpClient
import network.KtorClientProvider
import secrets.SecretsConfig

object IntegrationModule {
    fun provideGeminiClient(): GeminiClient = GeminiClient(KtorClientProvider.client , SecretsConfig.SecretsLoader("server/src/main/resources/secrets.json").init())
    fun provideMCPClient(): McpClient = McpClient()
}