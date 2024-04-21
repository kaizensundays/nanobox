package com.kaizensundays.fusion.nanobox

import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created: Saturday 3/23/2024, 12:19 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val context = ClassPathXmlApplicationContext("/NanoBoxContext.xml")
        context.registerShutdownHook()

        val lock = Object()
        Thread { synchronized(lock) { lock.wait() } }.start()
    }

}