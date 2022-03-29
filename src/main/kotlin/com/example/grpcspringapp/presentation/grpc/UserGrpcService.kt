package com.example.grpcspringapp.presentation.grpc

import com.example.grpcspringapp.application.UserService
import com.example.grpcspringapp.proto.common.Empty
import com.example.grpcspringapp.proto.common.Gender
import com.example.grpcspringapp.proto.user.UserGrpcKt
import com.example.grpcspringapp.proto.user.UserListResponse
import com.example.grpcspringapp.proto.user.UserRequest
import com.example.grpcspringapp.proto.user.UserResponse
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class UserGrpcService(
    private val userService: UserService
) : UserGrpcKt.UserCoroutineImplBase() {
    override suspend fun register(request: Empty): UserResponse {
        return this.userService.register().awaitFirst().let {
            UserResponse.newBuilder()
                .setId(it.id.toString())
                .setName(it.name)
                .setGender(Gender.forNumber(it.gender))
                .setEnabled(it.enabled)
                .build()
        }
    }

    override suspend fun getUser(request: UserRequest): UserResponse {
        return this.userService.getUser(request.id).awaitFirstOrNull()?.let {
            UserResponse.newBuilder()
                .setId(it.id.toString())
                .setName(it.name)
                .setGender(Gender.forNumber(it.gender))
                .setEnabled(it.enabled)
                .build()
        } ?: throw RuntimeException("ユーザーが見つかりませんでした")
    }

    override suspend fun getUsers(request: Empty): UserListResponse {
        return UserListResponse.newBuilder().addAllUserList(
            this.userService.getUsers().toIterable().map {
                UserResponse.newBuilder()
                    .setId(it.id.toString())
                    .setName(it.name)
                    .setGender(Gender.forNumber(it.gender))
                    .setEnabled(it.enabled)
                    .build()
            }
        ).build()
    }
}
