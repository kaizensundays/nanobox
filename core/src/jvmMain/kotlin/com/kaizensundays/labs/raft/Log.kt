package com.kaizensundays.labs.raft

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.charset.Charset
/**
 * Created: Monday 1/19/2026, 12:15 PM Eastern Time
 *
 * @author Sergey Chuykov & Junie (gpt-5-2025-08-07)
 */

// <<boundary>>
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Log actual constructor(private val path: String) {

    /**
     * Appends the given LogEntry to the log file specified by [path] as a single JSON line.
     * This function is pure with respect to input/output parameters but performs a file side-effect as boundary.
     */
    actual fun append(entry: LogEntry) {
        // Serialize entry to a compact JSON string
        val jsonLine: String = Json.encodeToString(entry) + "\n"

        // Append as UTF-8 text
        File(path).appendText(jsonLine, Charset.forName("UTF-8"))
    }
}
