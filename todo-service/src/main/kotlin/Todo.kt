import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Todos: UUIDTable() {
    val name = varchar("name", 255)
}

// DAO
class Todo(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object: UUIDEntityClass<Todo>(Todos)

    var name by Todos.name
}
