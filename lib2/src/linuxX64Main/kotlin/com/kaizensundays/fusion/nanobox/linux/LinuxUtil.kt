package com.kaizensundays.fusion.nanobox.linux

import kotlinx.cinterop.toKString
import platform.posix.getenv

/**
 * Created: Sunday 3/31/2024, 12:12 PM Eastern Time
 *
 * @author Sergey Chuykov
 */

fun getEnv(name: String): String? {
    return getenv(name)?.toKString()
}

fun getEnvAsInt(name: String, default: Int): Int {
    return getEnv(name)?.toInt() ?: default
}
