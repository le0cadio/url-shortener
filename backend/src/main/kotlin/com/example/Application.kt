package com.example

import com.example.controllers.UrlController
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.dao.ShortUrlDao
import io.ktor.http.*
import io.ktor.server.request.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    val dao = ShortUrlDao()
    routing {
        val controller = UrlController()

        post("/shorten") {
            try {
                val params = call.receiveParameters()
                val originalUrl = params["url"] ?: return@post call.respondText("Missing URL", status = HttpStatusCode.BadRequest)
                val shortUrl = controller.shortenUrl(originalUrl)
                call.respondText("Shortened URL: http://localhost:8080/${shortUrl.shortCode}")
            } catch (e: Exception) {
                e.printStackTrace()
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
            }
        }

        /* post("/shorten") {
            val params = call.receiveParameters()
            val originalUrl =
                params["url"] ?: return@post call.respondText("Missing URL", status = HttpStatusCode.BadRequest)
            val shortUrl = controller.shortenUrl(originalUrl)
            call.respondText("Shortened URL: http://localhost:8080/${shortUrl.shortCode}")
        } */

        get("/{code}") {
            val code = call.parameters["code"]
            val shortUrl = code?.let { controller.getOriginalUrl(it) }

            if (shortUrl == null) {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            } else {
                call.respondRedirect(shortUrl.originalUrl)
            }
        }

    }
}