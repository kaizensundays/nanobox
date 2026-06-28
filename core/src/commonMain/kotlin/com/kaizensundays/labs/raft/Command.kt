package com.kaizensundays.labs.raft

import kotlinx.serialization.Serializable
/**
 * Created: Monday 1/19/2026, 11:52 AM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Serializable
data class Command(
    val type: String,
    val key: String,
    val value: String,
)