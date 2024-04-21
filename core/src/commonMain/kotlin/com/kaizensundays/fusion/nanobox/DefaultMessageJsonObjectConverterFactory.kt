package com.kaizensundays.fusion.nanobox

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

/**
 * Created: Sunday 4/14/2024, 12:49 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class DefaultMessageJsonObjectConverterFactory : JsonObjectConverterFactory {

    override fun create(): JsonObjectConverter {

        val messageSerializerModule = SerializersModule {
            polymorphic(Message::class, Notification::class, serializer())
            polymorphic(Message::class, Status::class, serializer())
        }

        val jsonConf = Json {
            serializersModule = messageSerializerModule
            classDiscriminator = "@class"
            prettyPrint = true
        }

        return DefaultJsonObjectConverter(jsonConf)
    }

}