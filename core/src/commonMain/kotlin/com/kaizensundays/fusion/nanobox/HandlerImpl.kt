package com.kaizensundays.fusion.nanobox

/**
 * Created: Thursday 3/21/2024, 9:51 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HandlerImpl : Handler {
    override fun handle(msg: String): String
}