package config

import kotlinx.serialization.json.Json
import model.secrets.Secrets
import java.io.File

class Config {
    companion object SecretsLoader {
        private val secrets =  File("server/src/main/resources/secrets.json")
        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }
        fun init(): Secrets = json.decodeFromString<Secrets>(secrets.readText())
    }
}