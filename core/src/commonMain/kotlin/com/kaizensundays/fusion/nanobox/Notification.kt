package com.kaizensundays.fusion.nanobox

import kotlinx.serialization.Serializable

/**
 * Created: Saturday 4/13/2024, 1:45 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Serializable
data class Notification(val text: String) : Message()