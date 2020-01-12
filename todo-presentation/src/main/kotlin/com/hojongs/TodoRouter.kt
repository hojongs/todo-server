package com.hojongs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TodoRouter {

    @Bean
    fun router(handler: TodoHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/hello", handler::hello)
            GET("/todos", handler::listTodo)
            POST("/todos", handler::insertTodo) // body
            PUT("/todos/{id}", handler::updateTodo) // body
        }
    }
}
