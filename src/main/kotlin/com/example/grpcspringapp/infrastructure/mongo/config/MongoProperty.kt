package com.example.grpcspringapp.infrastructure.mongo.config

import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Primary
@Configuration
@ConfigurationProperties(prefix = "app.datasource.mongodb")
class MongoProperty : MongoProperties()
