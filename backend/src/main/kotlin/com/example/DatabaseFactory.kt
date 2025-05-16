package com.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

object Urls : Table() {
    val id = integer("id").autoIncrement()
    val originalUrl = varchar("original_url", 2048)
    val shortUrl = varchar("short_url", 20)
    val clicks = integer("clicks").default(0)

    override val primaryKey = PrimaryKey(id)
}

object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/shortener",
            driver = "org.posgresql.Driver",
            user = "postgres",
            password = "postgres",
        )

        transaction {
            SchemaUtils.create(Urls)
        }
    }
}