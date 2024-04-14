package com.kaizensundays.fusion

import com.kaizensundays.fusion.nanobox.Handler
import okio.FileSystem
import okio.Path.Companion.toPath
import org.junit.jupiter.api.Test

/**
 * Created: Thursday 3/21/2024, 9:56 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class HandlerJvmTest {

    private val handler = Handler()

    @Test
    fun handle() {
        handler.handle("Ok")
    }

    @Test
    fun loadProperties() {

        val fs = FileSystem.SYSTEM

        val path = fs.canonicalize("src/jvmTest/resources/test.properties".toPath())
        println("path=$path")

        val s = fs.read(path) { readUtf8() }
        println(s)
    }

}