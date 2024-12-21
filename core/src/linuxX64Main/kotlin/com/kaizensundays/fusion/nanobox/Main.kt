package com.kaizensundays.fusion.nanobox

import platform.posix.sleep

/**
 * Created: Saturday 3/23/2024, 12:47 PM Eastern Time
 *
 * @author Sergey Chuykov
 */

fun main() {

    val serverPort = getEnvAsInt("SERVER_PORT", 7700)

    val factory = DefaultMessageJsonObjectConverterFactory()
    val converter = factory.create()

    val server = KtorServer().apply {
        port = serverPort
        handler = HandlerImpl(converter)
    }
    server.start()

    sleep(60u)

    server.stop()
}