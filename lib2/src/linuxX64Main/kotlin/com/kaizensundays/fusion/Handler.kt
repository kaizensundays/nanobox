package com.kaizensundays.fusion

/**
 * Created: Saturday 3/23/2024, 12:48 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
actual class Handler {

    actual fun handle(msg: String): String {
        println(msg)
        return "Ok"
    }

}