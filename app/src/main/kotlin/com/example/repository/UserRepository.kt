package com.example.repository

import com.example.models.User
import com.example.tables.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction


class UserRepository {

    fun createUser(name: String, email: String, passwordHash: String): User {
        val id = transaction {
            UserTable.insert {
                it[UserTable.name] = name
                it[UserTable.email] = email
                it[UserTable.passwordHash] = passwordHash
            } get UserTable.id
        }

        return User(id, name, email)
    }

    fun findByEmail(email: String): UserWithPassword? {
        return transaction {
            UserTable.selectAll().where { UserTable.email eq email }
                .map {
                    UserWithPassword(
                        id = it[UserTable.id],
                        name = it[UserTable.name],
                        email = it[UserTable.email],
                        passwordHash = it[UserTable.passwordHash]
                    )
                }
                .singleOrNull()
        }
    }

      fun getById(id: Int): User? {
    return transaction {
        UserTable.select { UserTable.id eq id }
            .singleOrNull()
            ?.let { User(it[UserTable.id], it[UserTable.name], it[UserTable.email]) }
    }}


    fun add(user: User): User {
    val id = transaction {
        UserTable.insert {
            it[UserTable.name] = user.name
            it[UserTable.email] = user.email
        } get UserTable.id
    }
    return user.copy(id = id)
}

    fun delete(id: Int): Boolean {
    return transaction {
        val deletedCount = UserTable.deleteWhere { UserTable.id eq id }
        deletedCount > 0
    }
}



    fun getAll(): List<User> {
        return transaction {
            UserTable.selectAll().map {
                User(
                    it[UserTable.id],
                    it[UserTable.name],
                    it[UserTable.email]
                )
            }
        }
    }
}

data class UserWithPassword(
    val id: Int,
    val name: String,
    val email: String,
    val passwordHash: String
)
