package com.example.quarkusChatbot

import io.agroal.pool.MetricsRepository
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import javax.print.attribute.standard.Media
import kotlin.random.Random

@Path("/chat")
class ChatbotResource {

    private val responses = listOf("Hello !", "How can I help you ?", "Goodbye !")

    //Inject panache repo
    @Inject
    lateinit var repository: ChatMessageRepository

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun chat(message: Map<String, String>) : Response {
        val userMessage = message["userMessage"] ?: return Response.status(Response.Status.BAD_REQUEST).build()
        val botResponse = responses.random()

        val chatMessage = ChatMessage(userMessage = userMessage, botResponse = botResponse)
        chatMessage.persist()

        return Response.ok(chatMessage).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllConversations(): Response {
        val conversations = repository.listAll()
        return Response.ok(conversations).build()
    }
}