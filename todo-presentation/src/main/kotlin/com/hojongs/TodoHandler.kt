package com.hojongs

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class TodoHandler(val service: TodoService) {

    suspend fun hello(request: ServerRequest): ServerResponse = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValueAndAwait(
            mapOf(
                "message" to service.hello()
            )
        )

    suspend fun listTodo(request: ServerRequest): ServerResponse = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValueAndAwait(
            service.listTodo()
        )

    suspend fun insertTodo(request: ServerRequest): ServerResponse = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValueAndAwait(
            // TODO ServerRequest POST json body
            request.bodyToMono(Todo::class.java)
                .subscribe { service.insertTodo(it) })

    suspend fun updateTodo(request: ServerRequest): ServerResponse = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValueAndAwait(
            request.bodyToMono(Todo::class.java)
                .subscribe { todo -> service.updateTodo(
                    request.pathVariable("id"),
                    todo
                ) }
        )

}
