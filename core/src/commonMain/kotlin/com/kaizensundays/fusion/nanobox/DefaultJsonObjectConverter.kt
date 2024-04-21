package com.kaizensundays.fusion.nanobox

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

/**
 * Created: Sunday 4/14/2024, 12:22 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class DefaultJsonObjectConverter(private val converter: Json) : JsonObjectConverter {

    override fun <T : Any> fromObject(obj: T, baseClass: KClass<T>): String {
        return converter.encodeToString(PolymorphicSerializer(baseClass), obj)
    }

    override fun <T : Any> toObject(json: String, baseClass: KClass<T>): T {
        return converter.decodeFromString(PolymorphicSerializer(baseClass), json)
    }

}