package com.example.grpcspringapp.application

import com.example.grpcspringapp.domain.repository.UserRepository
import com.example.grpcspringapp.infrastructure.mongo.document.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register() = this.userRepository.save(User())

    fun getUser(id: String) = this.userRepository.findById(id)

    fun getUsers() = this.userRepository.findAll()
}