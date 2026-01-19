package com.kaizensundays.labs.raft

/**
 * Created: Monday 1/19/2026, 12:04 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Log(path: String) {
    fun append(entry: LogEntry)
}