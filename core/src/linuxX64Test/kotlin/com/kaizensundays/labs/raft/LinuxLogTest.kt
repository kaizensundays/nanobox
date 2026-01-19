package com.kaizensundays.labs.raft

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import platform.posix.FILE
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.remove
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created: Monday 1/19/2026, 12:45 PM Eastern Time
 *
 * @author Sergey Chuykov & Junie (gpt-5-2025-08-07)
 */
@OptIn(ExperimentalForeignApi::class)
class LinuxLogTest {

    private val testPath = "test_raft_linux.log"

    @BeforeTest
    fun setUp() {
        remove(testPath)
    }

    @AfterTest
    fun tearDown() {
        remove(testPath)
    }

    private fun readLines(path: String): List<String> {
        val file: CPointer<FILE>? = fopen(path, "r")
        if (file == null) return emptyList()

        val lines = mutableListOf<String>()
        val buffer = ByteArray(1024)
        buffer.usePinned { pinned ->
            while (true) {
                val result: CPointer<ByteVar>? = fgets(pinned.addressOf(0), buffer.size.convert(), file)
                if (result == null) break
                lines.add(result.toKString().trimEnd('\n'))
            }
        }

        fclose(file)
        return lines
    }

    @Test
    fun append_singleEntry_writesJsonLine() {
        val log = Log(testPath)

        val entry = LogEntry(
            index = 1L,
            term = 1L,
            command = "set x=1",
            timestamp = "2026-01-19T17:16:00Z",
            leaderId = "n1"
        )

        log.append(entry)

        val lines = readLines(testPath)
        assertEquals(1, lines.size)

        val decoded = Json.decodeFromString<LogEntry>(lines[0])
        assertEquals(entry, decoded)
    }

    @Test
    fun append_multipleEntries_areWrittenInOrder() {
        val log = Log(testPath)

        val entries = listOf(
            LogEntry(1, 1, "set a=1", "2026-01-19T17:16:10Z", "n1"),
            LogEntry(2, 1, "set b=2", "2026-01-19T17:16:20Z", "n1"),
            LogEntry(3, 2, "set c=3", "2026-01-19T17:16:30Z", "n2"),
        )

        entries.forEach { log.append(it) }

        val lines = readLines(testPath)
        assertEquals(entries.size, lines.size)

        val decoded = lines.map { Json.decodeFromString<LogEntry>(it) }
        assertEquals(entries, decoded)
    }
}
