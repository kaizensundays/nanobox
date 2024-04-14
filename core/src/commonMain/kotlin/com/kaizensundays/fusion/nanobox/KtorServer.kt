package com.kaizensundays.fusion.nanobox

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.request.receive
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
class KtorServer {

    private lateinit var logger: Logger
    private lateinit var engine: ApplicationEngine

    var port = 7700

    lateinit var handler: HandlerInf

    private suspend fun coroutine() = "(${kotlin.coroutines.coroutineContext[CoroutineName.Key]}) "

    private suspend fun startServer() {

        engine = embeddedServer(CIO, port = this.port) {
            install(Routing)
            routing {
                get("/ping") {
                    call.respondText("Ok")
                }
                post("/handle") {
                    val msg = call.receive<String>()
                    val result = handler.handle(msg)
                    call.respondText(result)
                }
            }
        }
        logger = engine.environment.log

        engine.environment.monitor.subscribe(ApplicationStarted) {
            logger.info("Ktor Server Started on port $port")
        }

        println("")
        logger.info(coroutine() + "Starting Ktor Server ...")
        engine.start(wait = true)
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