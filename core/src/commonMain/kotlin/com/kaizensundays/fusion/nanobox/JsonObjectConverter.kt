package com.kaizensundays.fusion.nanobox

import kotlin.reflect.KClass

/**
 * Created: Sunday 4/14/2024, 12:20 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
interface JsonObjectConverter {

    fun <T : Any> fromObject(obj: T, baseClass: KClass<T>): String

    fun <T : Any> toObject(json: String, baseClass: KClass<T>): T

}