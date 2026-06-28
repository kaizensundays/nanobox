package com.kaizensundays.labs.raft

import kotlinx.cinterop.CPointer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import platform.posix.FILE
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fputs
import platform.posix.perror

/**
 * Created: Monday 1/19/2026, 12:15 PM Eastern Time
 *
 * @author Sergey Chuykov & Junie (gpt-5-2025-08-07)
 */

// <<boundary>>
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Log actual constructor(private val path: String) {

    // Appends the entry as a single JSON line to the file at [path].
    @OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
    actual fun append(entry: LogEntry) {
        // Serialize to compact JSON and add trailing newline for line-delimited logs
        val jsonLine = Json.encodeToString(entry) + "\n"

        // Use POSIX append mode to ensure writes go to end-of-file
        val mode = "a"
        val file: CPointer<FILE>? = fopen(path, mode)
        if (file == null) {
            // Log the error to stderr using perror for native builds; in production we could propagate
            perror("fopen")
            return
        }

        // Write Kotlin String directly via fputs (interop maps to const char*)
        fputs(jsonLine, file)

        fclose(file)
    }
}
