package com.hojongs.todopresentation

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
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
}
