package Coder

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class JsonCoder : Coder {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    override fun contentType(): String = "application/json"

    override fun <T : Any> serialize(input: T): String {
        return json.encodeToString(serializer(input::class.java), input)
    }

    @OptIn(InternalSerializationApi::class)
    override fun <T : Any> deserialize(input: String, type: KClass<T>): T {
        return json.decodeFromString(type.serializer(), input)
    }
}