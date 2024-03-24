package com.kaizensundays.fusion

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created: Sunday 3/24/2024, 12:10 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class KtorServer {

    fun startServer() {
        println("startServer")
    }

    fun start() {

        CoroutineScope(Dispatchers.Default + CoroutineName("start")).launch { startServer() }

        println("Started")
    }

    fun stop() {

        println("Stopped")
    }

}