package com.kaizensundays.labs.raft

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created: Monday 1/19/2026, 12:16 PM Eastern Time
 *
 * @author Sergey Chuykov & Junie (gpt-5-2025-08-07)
 */
class LogJvmTest {

    private fun tempFile(): Path = Files.createTempFile("raft-log-", ".log").also {
        it.toFile().deleteOnExit()
    }

    @Test
    fun append_singleEntry_writesJsonLine() {
        val tmp = tempFile()
        val log = Log(tmp.toString())

        val entry = LogEntry(
            index = 1L,
            term = 1L,
            command = "set x=1",
            timestamp = "2026-01-19T17:16:00Z",
            leaderId = "n1"
        )

        log.append(entry)

        val lines = File(tmp.toString()).readLines(Charsets.UTF_8)
        assertEquals(1, lines.size)

        val decoded = Json.decodeFromString<LogEntry>(lines[0])
        assertEquals(entry, decoded)
    }

    @Test
    fun append_multipleEntries_areWrittenInOrder() {
        val tmp = tempFile()
        val log = Log(tmp.toString())

        val entries = listOf(
            LogEntry(1, 1, "set a=1", "2026-01-19T17:16:10Z", "n1"),
            LogEntry(2, 1, "set b=2", "2026-01-19T17:16:20Z", "n1"),
            LogEntry(3, 2, "set c=3", "2026-01-19T17:16:30Z", "n2"),
        )

        entries.forEach { log.append(it) }

        val lines = File(tmp.toString()).readLines(Charsets.UTF_8)
        assertEquals(entries.size, lines.size)

        val decoded = lines.map { Json.decodeFromString<LogEntry>(it) }
        assertEquals(entries, decoded)
    }
}
