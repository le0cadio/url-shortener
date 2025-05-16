package com.example

import org.jetbrains.exposed.sql.Table

object Urls : Table() {
    val id = integer("id").autoIncrement()
    val originalUrl = varchar("original_url", 1024)
    val shortenedPath = varchar("shortened_path", 100).uniqueIndex()
    val createdAt = long("Created_at")
    val expiresAt = long("expires_at").nullable()
    val clickCount = integer("click_count").default(0)

    override val primaryKey = PrimaryKey(id)
}