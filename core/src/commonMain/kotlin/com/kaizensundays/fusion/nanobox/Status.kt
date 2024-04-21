package com.kaizensundays.fusion.nanobox

import kotlinx.serialization.Serializable

/**
 * Created: Saturday 4/13/2024, 1:44 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Serializable
data class Status(val text: String) : Message()