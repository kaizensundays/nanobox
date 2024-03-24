package com.kaizensundays.fusion

import java.lang.Thread.sleep

/**
 * Created: Saturday 3/23/2024, 12:19 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val server = KtorServer()
        server.start()

        sleep(10_000)

        server.stop()
    }

}