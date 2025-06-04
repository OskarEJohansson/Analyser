package secrets

import kotlinx.serialization.json.Json
import java.io.File

class SecretsConfig {
        class SecretsLoader(private val path: String) {

            private val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            }

            fun init(): Secrets {
                val secrets =  File(path)
                if(!secrets.exists()) throw Exception("Secrets file not found")
                return json.decodeFromString<Secrets>(secrets.readText())
            }
        }
}