package com.kaizensundays.fusion.nanobox

import okio.FileSystem
import okio.Path.Companion.toPath
import org.junit.jupiter.api.Test

/**
 * Created: Saturday 3/30/2024, 7:10 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class FileSystemTest {

    @Test
    fun loadProperties() {

        val fs = FileSystem.SYSTEM

        val path = fs.canonicalize("src/jvmTest/resources/test.properties".toPath())
        println("path=$path")

        val s = fs.read(path) { readUtf8() }
        println(s)
    }

}