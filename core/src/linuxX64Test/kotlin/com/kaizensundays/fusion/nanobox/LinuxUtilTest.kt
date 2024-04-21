package com.kaizensundays.fusion.nanobox

import platform.posix.setenv
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created: Sunday 3/31/2024, 12:12 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class LinuxUtilTest {

    @Test
    fun test() {

        val serverPortProp = "SERVER_PORT"


        var port = getEnvAsInt(serverPortProp, 0)
        assertEquals(0, port)

        setenv(serverPortProp, "7711", 1)
        port = getEnvAsInt(serverPortProp, 0)
        assertEquals(7711, port)
    }

}