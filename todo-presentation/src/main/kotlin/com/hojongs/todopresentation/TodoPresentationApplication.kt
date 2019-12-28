package com.hojongs.todopresentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoPresentationApplication

fun main(args: Array<String>) {
	runApplication<TodoPresentationApplication>(*args)
}
