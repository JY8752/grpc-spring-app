package com.example.grpcspringapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [MongoAutoConfiguration::class])
class GrpcSpringAppApplication

fun main(args: Array<String>) {
    runApplication<GrpcSpringAppApplication>(*args)
}
