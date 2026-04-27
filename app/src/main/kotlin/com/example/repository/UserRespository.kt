package com.example.repository

import com.example.models.User

class UserRepository {

    private val users = mutableListOf(
        User(1, "Sudeshna", "sudeshna0501@outlook.in"),
        User(2, "John", "johnKelly@gmail.com")
    )

    fun getAll(): List<User> {
        return users
    }

    fun getById(id: Int): User? {
        return users.find { it.id == id }
    }

    fun add(user: User): User {
        users.add(user)
        return user
    }

    fun delete(id: Int): Boolean {
        return users.removeIf { it.id == id }
    }
}