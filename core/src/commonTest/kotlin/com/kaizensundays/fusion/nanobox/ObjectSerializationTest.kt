package com.kaizensundays.fusion.nanobox

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import kotlin.test.Test
import kotlin.test.assertEquals
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

        val jsonConf = Json {
            serializersModule = messageSerializerModule
            classDiscriminator = "@class"
            prettyPrint = true
        }

        val converter: JsonObjectConverter = DefaultJsonObjectConverter(jsonConf)

        var json = converter.fromObject(Notification("Ok"), Message::class)
        println(json)
        assertTrue(json.matches(Regex("(?s).*@class.*.Notification.*")))

        var msg = converter.toObject(json, Message::class)
        assertTrue(msg is Notification)
        assertEquals("Ok", msg.text)

        json = converter.fromObject(Status("Up"), Message::class)
        println(json)
        assertTrue(json.matches(Regex("(?s).*@class.*.Status.*")))

        msg = converter.toObject(json, Message::class)
        assertTrue(msg is Status)
        assertEquals("Up", msg.text)
    }

}