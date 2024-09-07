package com.example.quarkusChatbot

import dev.langchain4j.service.MemoryId
import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.RegisterAiService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
@RegisterAiService
interface IaService {
    @SystemMessage(
        """
        You are a helpful assistant.
        You are chating with humans and need to provide as much informations as possible.
        Do not respond to yourself.
    """
    )
    @UserMessage(
        """
        {userMessage}
    """
    )
    fun iaRespond(@MemoryId memoryId : String, userMessage: String): String
}