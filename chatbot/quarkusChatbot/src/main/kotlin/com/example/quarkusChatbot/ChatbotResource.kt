package com.example.quarkusChatbot

import io.agroal.pool.MetricsRepository
import io.quarkus.hibernate.reactive.panache.Panache
import io.smallrye.common.annotation.NonBlocking
import io.smallrye.common.annotation.Blocking
import io.smallrye.common.annotation.RunOnVirtualThread
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.uni
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.enterprise.context.ApplicationScoped
import org.jboss.resteasy.reactive.RestResponse
import javax.print.attribute.standard.Media
import kotlin.random.Random

@Path("/chat")
@ApplicationScoped
//@NonBlocking
class ChatbotResource {

    private val responses = listOf("Hello !", "How can I help you ?", "Goodbye !")

    //Inject panache repo
    @Inject
    lateinit var repository: ChatMessageRepository

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NonBlocking
    fun chat(message: Map<String, String>) : Uni<Response> {
        //val userMessage = message["userMessage"] ?: return Response.status(Response.Status.BAD_REQUEST).build()
        val userMessage = message["userMessage"] ?: return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).build())
        val botResponse = responses.random()

        val chatMessage = ChatMessage(userMessage = userMessage, botResponse = botResponse)
        //chatMessage.persist()

        //return Response.ok(chatMessage).build()
        /*return Panache.withTransaction(chatMessage::persist)
            .replaceWith(Response.status(Response.Status.CREATED)
                .build())*/
        return Panache.withTransaction(chatMessage::persist)
            .replaceWith(Response.ok(chatMessage).build())
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @NonBlocking
    fun getAllConversations(): Uni<Response> {
        val conversations = repository.listAll()
        //return Response.ok(conversations).build()
        return Uni.createFrom().item(Response.ok(conversations).build())
    }*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @NonBlocking
    fun getAllConversations(): Uni<Response> {
        val conversations = repository.listAll()
        return Uni.createFrom().item(Response.ok(conversations).build())
    }
}