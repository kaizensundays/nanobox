package com.kaizensundays.fusion.nanobox

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created: Thursday 3/21/2024, 9:54 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
actual class Handler(private val jsonConverter: JsonObjectConverter) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    actual fun handle(msg: String): String {
        println(msg)

        return try {
            val obj = jsonConverter.toObject(msg, Message::class)
            println(obj)
            "Ok"
        } catch (e: Exception) {
            logger.error("", e)
            e.message ?: ""
        }

    }

}
