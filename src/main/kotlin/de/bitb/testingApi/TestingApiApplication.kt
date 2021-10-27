package de.bitb.testingApi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestingApiApplication

fun main(args: Array<String>) {
	runApplication<TestingApiApplication>(*args)
}
