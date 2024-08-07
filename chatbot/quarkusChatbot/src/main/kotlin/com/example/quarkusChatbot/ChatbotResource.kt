package com.example.quarkusChatbot

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import kotlin.collections.ArrayList

@Path("/chat")
class ChatbotResource {

    data class ChatMessage(val userMessage: String, val botResponse: String)

    private val responses = listOf("Hello!", "How can I help you?", "Goodbye!")

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun chat(message: Map<String, String>): Response {
        val userMessage = message["userMessage"] ?: return Response.status(Response.Status.BAD_REQUEST).build()
        val botResponse = responses.random()
        // Enregistrer dans la base de données
        return Response.ok(ChatMessage(userMessage, botResponse)).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllConversations(): Response {
        // Récupérer toutes les conversations depuis la base de données
        val conversations = ArrayList<ChatMessage>()
        return Response.ok(conversations).build()
    }
}