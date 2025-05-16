package com.example.controllers

import com.example.dao.ShortUrlDao
import com.example.models.ShortUrl
import java.util.*

class UrlController(
    private val dao: ShortUrlDao = ShortUrlDao()
) {
    fun shortenUrl(originalUrl: String): ShortUrl {
        val shortCode = UUID.randomUUID().toString().take(8)
        return dao.addUrl(originalUrl, shortCode)
    }

    fun getOriginalUrl(shortCode: String): ShortUrl? {
        return dao.findByShortCode(shortCode)
    }
}
