package com.example.grpcspringapp

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class PropertyConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}
