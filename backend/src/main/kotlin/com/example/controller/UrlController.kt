package com.example.controller

import com.example.dao.ShortUrlDao
import com.example.models.ShortUrl
import java.util.*

class UrlController(
    private val dao: ShortUrlDao = ShortUrlDao()
) {
    fun shortenUrl(originalUrl: String): ShortUrl {
        val formattedUrl = if (originalUrl.startsWith("http://") || originalUrl.startsWith("https://")) {
            originalUrl
        } else {
            "http://$originalUrl"
        }

        val shortCode = UUID.randomUUID().toString().take(8)
        return dao.addUrl(formattedUrl, shortCode)
    }

    fun getOriginalUrl(shortCode: String): ShortUrl? {
        return dao.findByShortCode(shortCode)
    }
}
