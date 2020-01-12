import com.hojongs.Todo
import com.hojongs.Todos
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

class TodoServiceTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun connect() {
            Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
        }

        fun <T> transactionWithLogger(statement: () -> T) {
            return transaction {
                addLogger(StdOutSqlLogger)

                statement()
            }
        }
    }

    @BeforeEach
    fun resetSchema() {
        transactionWithLogger {
            SchemaUtils.drop(Todos)
            SchemaUtils.create(Todos)
        }
    }

    @Test
    fun testTodo() {
        transactionWithLogger {
            val todoId = Todos.insert {
                it[name] = "Create git repo"
            } get Todos.id

            assertEquals(
                "Create git repo",
                Todos.select { Todos.id eq todoId }.firstOrNull()?.get(Todos.name)
            )

            assertNull(
                Todos.select { Todos.id neq todoId }.firstOrNull()
            )
        }
    }

    @Test fun testDAO() {
        transactionWithLogger {
            val todo = Todo.new {
                name = "Create git repo"
            }

            assertEquals(todo, Todo[todo.id])
        }
    }
}
