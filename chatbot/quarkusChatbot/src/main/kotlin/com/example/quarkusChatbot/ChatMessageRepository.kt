package com.example.quarkusChatbot

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ChatMessageRepository : PanacheRepository<ChatMessage> {

    fun findByUserMessage(userMessage: String): List<ChatMessage> {
        return list("user_message", userMessage)
    }
}