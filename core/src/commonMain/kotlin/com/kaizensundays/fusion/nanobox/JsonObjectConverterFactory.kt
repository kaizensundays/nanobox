package com.kaizensundays.fusion.nanobox

/**
 * Created: Sunday 4/14/2024, 12:48 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
fun interface JsonObjectConverterFactory {

    fun create(): JsonObjectConverter

}