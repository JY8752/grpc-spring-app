package com.example.grpcspringapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcSpringAppApplication

fun main(args: Array<String>) {
    runApplication<GrpcSpringAppApplication>(*args)
}
