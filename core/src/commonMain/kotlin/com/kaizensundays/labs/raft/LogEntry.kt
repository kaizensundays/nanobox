package com.kaizensundays.labs.raft

import kotlinx.serialization.Serializable
/**
 * Created: Monday 1/19/2026, 11:50 AM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Serializable
data class LogEntry(
    val index: Long,
    val term: Long,
    val command: String,
    val timestamp: String,
    val leaderId: String
)