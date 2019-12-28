package com.hojongs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TodoServiceProvider {

    @Bean
    fun todoService() = TodoService()
}
