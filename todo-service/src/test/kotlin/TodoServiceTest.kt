import com.hojongs.Todos
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll

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

            Todos.selectAll().forEach {
                println("todo.name: ${it[Todos.name]}")
            }

            SchemaUtils.drop(Todos)
        }
    }
}
