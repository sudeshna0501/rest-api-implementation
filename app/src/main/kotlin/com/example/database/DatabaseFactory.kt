package com.example.database

import com.example.tables.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5433/ktor_db",
            driver = "org.postgresql.Driver",
            user = "ktor_user",
            password = "ktor_password"
        )

        transaction {
            SchemaUtils.create(UserTable)
        }
    }
}