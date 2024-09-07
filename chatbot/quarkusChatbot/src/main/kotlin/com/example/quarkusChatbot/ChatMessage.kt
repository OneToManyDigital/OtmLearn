package com.example.quarkusChatbot

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Cacheable
import jakarta.ws.rs.core.Response

@Entity
@Table(name = "conversation")
@Cacheable
data class ChatMessage(
    @Column(name = "user_message", length = 1000)
    var userMessage: String,

    @Column(name = "bot_response", length = 1000)
    var botResponse: String
) : PanacheEntity() {
    constructor() : this("","")
}