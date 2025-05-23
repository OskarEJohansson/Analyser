package Coder

import kotlin.reflect.KClass

interface Coder {
    fun contentType(): String

    fun <T : Any> serialize(input: T): String
    fun <T : Any> deserialize(input: String, type: KClass<T>): T
}