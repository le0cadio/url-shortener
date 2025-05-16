package com.example

import com.example.controller.UrlController
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.request.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    val controller = UrlController()

    routing {
        post("/shorten") {
            val params = call.receiveParameters()
            val originalUrl =
                params["url"] ?: return@post call.respondText("Missing URL", status = HttpStatusCode.BadRequest)
            val shortUrl = controller.shortenUrl(originalUrl)
            call.respondText("Shortened URL: http://localhost:8080/s/${shortUrl.shortCode}")
        }

        get("/s/{code}") {
            val code = call.parameters["code"]
            println("Recebido código: $code")  // debug

            if (code == null) {
                call.respondText("Código não fornecido", status = HttpStatusCode.BadRequest)
                return@get
            }

            if (code.contains("http")) {
                call.respondText("Código inválido", status = HttpStatusCode.BadRequest)
                return@get
            }

            val shortUrl = controller.getOriginalUrl(code)

            if (shortUrl == null) {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            } else {
                call.respondRedirect(shortUrl.originalUrl)
            }
        }

    }

}