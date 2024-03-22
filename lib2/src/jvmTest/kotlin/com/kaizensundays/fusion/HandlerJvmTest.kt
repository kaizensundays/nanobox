package com.kaizensundays.fusion

import org.junit.jupiter.api.Test

/**
 * Created: Thursday 3/21/2024, 9:56 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class HandlerJvmTest {

    private val handler = HandlerJvm()

    @Test
    fun test() {
        handler.handle("Ok")
    }

}