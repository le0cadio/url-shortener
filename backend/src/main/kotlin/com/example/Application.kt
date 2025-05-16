package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        routing {
            get("/") {
                call.respondText("Hello World!")
            }
            post("/") {
                call.respondText("Hello World! 1")
            }
        }

    }.start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
}