package com.hojongs

import org.jetbrains.exposed.dao.id.UUIDTable

object Todos: UUIDTable() {
    val name = varchar("name", 255)
}
