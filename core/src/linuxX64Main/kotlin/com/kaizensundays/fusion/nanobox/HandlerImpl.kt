package com.kaizensundays.fusion.nanobox

/**
 * Created: Saturday 3/23/2024, 12:48 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HandlerImpl(private val jsonConverter: JsonObjectConverter) : Handler {

    actual override fun handle(msg: String): String {
        println(msg)

        return try {
            val obj = jsonConverter.toObject(msg, Message::class)
            println(obj)
            "Ok"
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            e.message ?: ""
        }
    }

}