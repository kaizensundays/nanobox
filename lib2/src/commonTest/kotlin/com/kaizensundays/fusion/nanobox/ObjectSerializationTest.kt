package com.kaizensundays.fusion.nanobox

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Created: Saturday 4/13/2024, 1:25 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class ObjectSerializationTest {

    @Test
    fun test() {

        val messageSerializerModule = SerializersModule {
            polymorphic(Message::class, Notification::class, serializer())
            polymorphic(Message::class, Status::class, serializer())
        }

        val jsonConverter = Json {
            serializersModule = messageSerializerModule
            classDiscriminator = "@class"
        }

        var json = jsonConverter.encodeToString(PolymorphicSerializer(Message::class), Notification("Ok"))
        assertTrue(json.matches(".*@class.*.Notification.*".toRegex()))

        json = jsonConverter.encodeToString(PolymorphicSerializer(Message::class), Status("Up"))
        assertTrue(json.matches(".*@class.*Status.*".toRegex()))

    }

}