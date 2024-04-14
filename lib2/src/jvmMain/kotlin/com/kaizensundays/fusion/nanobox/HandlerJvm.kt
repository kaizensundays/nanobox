package com.kaizensundays.fusion.nanobox

/**
 * Created: Thursday 3/21/2024, 9:54 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
actual class Handler {

    actual fun handle(msg: String): String {
        println(msg)
        return "Ok"
    }

}
