package com.kaizensundays.fusion.nanobox

import okio.FileSystem
import okio.Path.Companion.toPath
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

/**
 * Created: Thursday 3/21/2024, 9:56 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class HandlerJvmTest {

    private val handler = HandlerImpl(mock())

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