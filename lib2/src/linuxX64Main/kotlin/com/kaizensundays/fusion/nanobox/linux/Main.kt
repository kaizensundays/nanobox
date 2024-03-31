package com.kaizensundays.fusion.nanobox.linux

import com.kaizensundays.fusion.KtorServer
import platform.posix.sleep

/**
 * Created: Saturday 3/23/2024, 12:47 PM Eastern Time
 *
 * @author Sergey Chuykov
 */

fun main() {

    val port = getEnvAsInt("SERVER_PORT", 7700)

    val server = KtorServer(port)
    server.start()

    sleep(60)

    server.stop()
}