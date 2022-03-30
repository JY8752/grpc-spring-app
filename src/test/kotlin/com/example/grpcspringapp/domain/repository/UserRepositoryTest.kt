package com.example.grpcspringapp.domain.repository

import com.example.grpcspringapp.infrastructure.mongo.document.User
import com.example.grpcspringapp.proto.common.Gender
import io.kotest.core.spec.style.FunSpec
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@SpringBootTest
class UserRepositoryTest(
    private val userRepository: UserRepository
) : FunSpec({
    context("正常系") {
        test("find and save") {
            // save
            val user = User(
                name = "test",
                gender = Gender.FEMALE.number
            )
            val response = userRepository.save(user)

            assertUser(response) { it.id != null }
            assertUser(response) { it.name == "test" }
            assertUser(response) { it.gender == Gender.FEMALE.number }
            assertUser(response) { it.enabled }

            // find
            val find = userRepository.findById(response.awaitSingle().id.toString())
            assertUser(find) { it.id != null }
            assertUser(find) { it.name == "test" }
            assertUser(find) { it.gender == Gender.FEMALE.number }
            assertUser(find) { it.enabled }
        }
    }
})

private fun assertUser(response: Mono<User>, callback: (User) -> Boolean) {
    StepVerifier.create(response)
        .expectNextMatches(callback)
        .expectComplete()
        .verify()
}
