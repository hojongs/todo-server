package com.hojongs

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest
@AutoConfigureWebTestClient
class TodoPresentationApplicationTests {
    // TODO test rule to use h2 db and create dummy data

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun testHello() {
        client.get()
            .uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.message")
            .isEqualTo("hello")
    }

    @Test
    fun testInsertListTodo() {
        val todo = transaction {
            Todo.new {
                name = "Create git repo"
            }
        }

        // TODO request body
        val m = jacksonObjectMapper()
        val json: String = m.writeValueAsString(todo)

        client.post()
            .bodyValue(
                mapOf("todos" to json)
            )
//            .uri("/todos")

        client.get()
            .uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.message")
            .isEqualTo("hello")
    }
}
