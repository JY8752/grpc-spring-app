package com.example.grpcspringapp.infrastructure.mongo.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories
class MongoConfig(
    private val mongoProperty: MongoProperty
) : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName(): String {
        return this.mongoProperty.mongoClientDatabase
    }

    override fun reactiveMongoClient(): MongoClient {
        // カスタム設定が必要な場合は下記で設定する
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(this.mongoProperty.uri))
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    // _classフィールドが入らないようになると思う
    override fun mappingMongoConverter(
        databaseFactory: ReactiveMongoDatabaseFactory,
        customConversions: MongoCustomConversions,
        mappingContext: MongoMappingContext
    ): MappingMongoConverter {
        val converter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return converter
    }

    @Bean
    fun mongoTemplate() =
        ReactiveMongoTemplate(
            this.mongoDatabaseFactory(),
            this.mappingMongoConverter(this.mongoDatabaseFactory(), this.customConversions(), MongoMappingContext())
        )

    @Bean
    fun mongoDatabaseFactory() =
        SimpleReactiveMongoDatabaseFactory(this.reactiveMongoClient(), this.databaseName)
}
