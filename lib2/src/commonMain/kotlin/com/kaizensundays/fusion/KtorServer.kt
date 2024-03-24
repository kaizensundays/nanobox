package com.kaizensundays.fusion

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
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

    private lateinit var logger: Logger
    private lateinit var engine: ApplicationEngine

    private fun startServer() {

        engine = embeddedServer(CIO, port = this.port) {
            install(Routing)
            routing {
                get("/ping") {
                    call.respondText("Ok")
                }
            }
        }
        logger = engine.environment.log

        logger.info("Starting Ktor Server ...")
        engine.start(wait = true)

        logger.info("Started")
    }

    fun start() {

        CoroutineScope(Dispatchers.Default + CoroutineName("start")).launch { startServer() }
    }

    fun stop() {

        engine.stop(3000L, 7000L)

        logger.info("Stopped")
    }

}