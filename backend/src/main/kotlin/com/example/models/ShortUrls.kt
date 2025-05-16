package com.example.models

import org.jetbrains.exposed.sql.Table
import java.util.UUID

object ShortUrls : Table("short_urls") {
    val id = uuid("id").autoGenerate()
    val originalUrl = text("original_url")
    val shortCode = varchar("short_code", 10).uniqueIndex()
    val clickCount = integer("click_count")

    override val primaryKey = PrimaryKey(id)
}

