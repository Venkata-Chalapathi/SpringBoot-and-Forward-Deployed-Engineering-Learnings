package com.example.demo.service;

import com.example.demo.aitools.CalculatorTool;
import com.example.demo.aitools.CurrencyExchangeTool;
import com.example.demo.aitools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private ChatClient chatClient;

    private CalculatorTool calculatorTool;

    private WeatherTool weatherTool;

    private CurrencyExchangeTool currencyExchangeTool;

    private List<Message> history = new ArrayList();

    private static final String SYSTEM_PROMPT = """
        You are a helpful AI assistant with access to external tools.
    
        Follow these rules:
        1. For arithmetic calculations, ALWAYS use the calculator tool.
        2. For current weather, ALWAYS use the currentWeather tool.
        3. For currency conversion or exchange rates, ALWAYS use the CurrencyExchangeTool
        4. You may call multiple tools when solving a multi-step request.
        5. After receiving tool results, explain the answer naturally.
        6. Never invent current weather or exchange-rate information.
        """;

    public ChatService(ChatClient.Builder builder,
                       CalculatorTool calculatorTool,
                       WeatherTool weatherTool,
                       CurrencyExchangeTool currencyExchangeTool){
        this.chatClient = builder.build();
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;
        this.currencyExchangeTool = currencyExchangeTool;
    }

    public String chat(String message){

        // USER Role
        history.add(new UserMessage(message));

        // SYSTEM + Conversation History
        String output = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(history)
                .tools(calculatorTool, weatherTool, currencyExchangeTool)
                .call()
                .content();

        // ASSISTANT Role
        history.add(new AssistantMessage(output));

        return output;
    }
}
