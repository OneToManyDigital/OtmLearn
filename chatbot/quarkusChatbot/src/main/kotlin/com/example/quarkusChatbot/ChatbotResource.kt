package com.example.quarkusChatbot

import io.quarkus.hibernate.reactive.panache.Panache
import io.smallrye.mutiny.Uni
import io.vertx.core.Vertx
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.RestQuery

@Path("/chat")
class ChatbotResource {

    private val responses = listOf("Hello !", "How can I help you ?", "Goodbye !")

    //Inject panache repo
    @Inject
    lateinit var repository: ChatMessageRepository

    @Inject
    lateinit var iaService: IaService

    @Inject
    lateinit var vertx: Vertx

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun chat(message: Map<String, String>): Uni<Response> {
        //val userMessage = message["userMessage"] ?: return Response.status(Response.Status.BAD_REQUEST).build()
        System.out.println(message)
        val userMessage =
            message["userMessage"] ?: return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).build())
        val memoryId =
            message["userId"] ?: return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).build())
        //val botResponse = responses.random()
        System.out.println("iaRespond Invoked")
        return Uni.createFrom()
            .emitter {
                vertx
                    .executeBlocking { ->
                        iaService.iaRespond(memoryId=memoryId, userMessage=userMessage)
                    }.onSuccess { res ->
                        it.complete(res)
                    }
                    .onFailure { t ->
                        it.fail(t)
                    }

            }
            .flatMap { botResponse ->
                val chatMessage = ChatMessage(userMessage = userMessage, botResponse = botResponse)
                Panache.withTransaction(chatMessage::persist)
                    .replaceWith(Response.ok(chatMessage).build())
            }


        //chatMessage.persist()

        //return Response.ok(chatMessage).build()
        /*return Panache.withTransaction(chatMessage::persist)
            .replaceWith(Response.status(Response.Status.CREATED)
                .build())*/

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
    fun getAllConversations(): Uni<Response> {
        return Uni.createFrom()
            .emitter {
                vertx
                    .executeBlocking { ->
                        val conversations = repository.listAll()
                    }.onSuccess { res ->
                        it.complete(res)
                    }.onFailure { t ->
                        it.fail(t)
                    }
            }
            .flatMap { conversations ->
                Uni.createFrom().item(Response.ok(conversations).build())
            }
    }
}