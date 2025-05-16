package com.example.models

import java.util.*

data class ShortUrl(
    val id: UUID?, val originalUrl: String, val shortCode: String, val clickCount: Int
)