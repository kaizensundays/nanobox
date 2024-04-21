package com.kaizensundays.fusion.nanobox

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


}