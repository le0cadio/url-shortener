package com.example.dao

import com.example.models.ShortUrl
import com.example.models.ShortUrls
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class ShortUrlDao {

    fun addUrl(originalUrl: String, shortCode: String): ShortUrl = transaction {
        val insertStatement = ShortUrls.insert {
            it[id] = UUID.randomUUID()
            it[ShortUrls.originalUrl] = originalUrl
            it[ShortUrls.shortCode] = shortCode
            it[clickCount] = 0
        }

        ShortUrl(
            id = insertStatement[ShortUrls.id], originalUrl = originalUrl, shortCode = shortCode, clickCount = 0
        )
    }

    fun findByShortCode(shortCode: String): ShortUrl? = transaction {
        ShortUrls.select { ShortUrls.shortCode eq shortCode }.map {
            ShortUrl(
                id = it[ShortUrls.id],
                originalUrl = it[ShortUrls.originalUrl],
                shortCode = it[ShortUrls.shortCode],
                clickCount = it[ShortUrls.clickCount]
            )
        }.singleOrNull()
    }

}


