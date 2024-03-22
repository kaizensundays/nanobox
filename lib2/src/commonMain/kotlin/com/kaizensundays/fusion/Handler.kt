package com.kaizensundays.fusion

/**
 * Created: Thursday 3/21/2024, 9:51 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
interface Handler {

    //fun handle(msg: String): String

}

expect fun Handler.handle(msg: String): String