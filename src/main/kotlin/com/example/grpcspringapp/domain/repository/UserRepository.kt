package com.example.grpcspringapp.domain.repository

import com.example.grpcspringapp.infrastructure.mongo.document.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ReactiveMongoRepository<User, String>
