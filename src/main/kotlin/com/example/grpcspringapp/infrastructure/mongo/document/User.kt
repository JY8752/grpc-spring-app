package com.example.grpcspringapp.infrastructure.mongo.document

import com.example.grpcspringapp.proto.common.Gender
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

private const val NAME = "nm"
private const val GENDER = "gnd"
private const val ENABLED = "enb"

@Document(collection = "user")
data class User(
    @Id val id: ObjectId? = null,
    @Field(value = NAME) val name: String = "test",
    @Field(value = GENDER) val gender: Int = Gender.MALE.number,
    @Field(value = ENABLED) val enabled: Boolean = true
) {
    companion object {
        fun getNameField() = NAME
        fun getGenderField() = GENDER
        fun getEnabledField() = ENABLED
    }
}
