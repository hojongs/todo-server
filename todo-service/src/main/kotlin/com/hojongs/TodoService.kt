package com.hojongs

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class TodoService {

    fun hello() = "hello"

    fun listTodo() = transaction {
        return@transaction mapOf(
            "todos" to Todos.selectAll().toList()
        )
    }

    fun insertTodo(todo: Todo) = transaction {
        Todos.insert { it[Todos.name] = name }
    }

    fun updateTodo(todoId: UUID, todo: Todo) = transaction {
        Todo.get(todoId).apply {
            this.name = todo.name
        }
    }
}
