package in.gvc.rag_ecommerce_support;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ChatBotController {

    private ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService){
        this.chatBotService = chatBotService;
    }

    @GetMapping("/query")
    public String query(@RequestParam String question) {
        return chatBotService.answerUserQuery(question);
    }
}
