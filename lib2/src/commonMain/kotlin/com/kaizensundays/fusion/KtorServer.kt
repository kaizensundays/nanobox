package com.kaizensundays.fusion

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext

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

    private suspend fun coroutine() = "(${kotlin.coroutines.coroutineContext[CoroutineName.Key]}) "

    private suspend fun startServer() {

        engine = embeddedServer(CIO, port = this.port) {
            install(Routing)
            routing {
                get("/ping") {
                    call.respondText("Ok")
                }
            }
        }
        logger = engine.environment.log

        println("")
        logger.info(coroutine() + "Starting Ktor Server ...")
        engine.start(wait = false)

        logger.info("Started")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun start() {

        val dispatcher = newFixedThreadPoolContext(4, "nano")

        CoroutineScope(dispatcher + CoroutineName("startServer")).launch {
            startServer()
        }
    }

    fun stop() {

        engine.stop(3000L, 7000L)

        logger.info("Stopped")
    }

}