package com.kaizensundays.fusion

import platform.posix.sleep

/**
 * Created: Saturday 3/23/2024, 12:47 PM Eastern Time
 *
 * @author Sergey Chuykov
 */

fun main() {

    val server = KtorServer()
    server.start()

    sleep(10)

    server.stop()
}