package com.kaizensundays.fusion

/**
 * Created: Saturday 3/23/2024, 12:19 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val server = KtorServer(7711)
        server.start()

        Runtime.getRuntime().addShutdownHook(Thread({ server.stop() }, "shutdown"))

        val lock = Object()

        Thread { synchronized(lock) { lock.wait() } }.start()
    }

}