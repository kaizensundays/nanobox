package com.kaizensundays.fusion

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created: Sunday 3/24/2024, 12:10 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class KtorServer(
    private val port: Int
) {

    private var engine: ApplicationEngine? = null

    private fun startServer() {
        println("startServer")

        embeddedServer(CIO, port = this.port) {
            install(Routing)
            routing {
                get("/ping") {
                    call.respondText("Ok")
                }
            }
        }.start(wait = true)

    }

    fun start() {

        CoroutineScope(Dispatchers.Default + CoroutineName("start")).launch { startServer() }

        println("Started")
    }

    fun stop() {

        engine?.stop(3000L, 7000L)

        println("Stopped")
    }

}