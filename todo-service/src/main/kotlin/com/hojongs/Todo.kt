package com.hojongs

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

// DAO
class Todo(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Todo>(Todos)

    var name by Todos.name
}
