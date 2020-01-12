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
import org.junit.jupiter.api.Test

class TodoServiceTest {

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun before() {
            Database.connect("jdbc:h2:mem:test", "org.h2.Driver")
        }
    }

    @Test
    fun testTodo() {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Todos)

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

            SchemaUtils.drop(Todos)
        }
    }
}
