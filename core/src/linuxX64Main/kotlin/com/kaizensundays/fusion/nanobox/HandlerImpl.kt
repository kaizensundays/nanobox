package com.kaizensundays.fusion.nanobox

/**
 * Created: Saturday 3/23/2024, 12:48 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
actual class HandlerImpl : Handler {

    override fun handle(msg: String): String {
        println(msg)
        return "Ok"
    }

}