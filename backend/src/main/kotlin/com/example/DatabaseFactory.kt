package com.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection
import com.example.Urls


object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/shortener",
            driver = "org.postgresql.Driver",
            user = "shortener",
            password = "123456",
        )

        transaction {
            SchemaUtils.create(Urls)
        }
    }
}