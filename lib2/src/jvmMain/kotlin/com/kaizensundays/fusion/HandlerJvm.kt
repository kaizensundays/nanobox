package com.kaizensundays.fusion

/**
 * Created: Thursday 3/21/2024, 9:54 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class HandlerJvm : Handler {
}

actual fun Handler.handle(msg: String): String {
    println(msg)
    return "Ok"
}