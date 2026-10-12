package com.example.FDE_Streaming;


import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class ChatService {

    private ChatClient chatClient;

    private List<Message> history = new ArrayList<>();

    public ChatService (ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    private String SYSTEM_PROMPT = """
            You are a helpful AI assistant.
                        Answer clearly and accurately.
                        Remember the context of the conversation.
            """;

    StringBuilder fullResponse = new StringBuilder();

    public Flux<String> chat(String message) {

        // USER Role
        history.add(new UserMessage(message));

        // SYSTEM + Conversation History
        Flux<String> response = chatClient.prompt()
                .system(SYSTEM_PROMPT)
//                .messages(history)
//                .user(message)
                .stream()
                .content()
                .doOnNext(fullResponse::append)
                .doOnComplete( () -> {
                    history.add(new AssistantMessage(fullResponse.toString()));
                });

        // ASSISTANT Role
//        history.add(new AssistantMessage(response));

        return response;

    }
}
