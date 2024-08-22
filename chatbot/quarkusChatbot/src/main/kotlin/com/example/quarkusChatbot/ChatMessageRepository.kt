package com.example.quarkusChatbot

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ChatMessageRepository : PanacheRepository<ChatMessage> {

    fun findByUserMessage(userMessage: String): Uni<List<ChatMessage>> {
        return list("user_message", userMessage)
    }
}