package in.gvc.rag_ecommerce_support;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBotService {

    private VectorStore vectorStore;
    private ChatClient chatClient;

    @Value("classpath*:knowledge/*.pdf")
    private Resource[] policyFiles;

    public ChatBotService(VectorStore vectorStore, ChatClient.Builder ChatClientbuilder) {
        this.vectorStore = vectorStore;
        this.chatClient = ChatClientbuilder.build();
    }

    @PostConstruct
    public void loadKnowledgeBase() {
        List<Document> allChunks = new ArrayList<>();

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(300)
                .build();

        for (Resource resource : policyFiles) {
            PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);
            List<Document> pages = reader.read();
            allChunks.addAll(splitter.split(pages));
        }

        vectorStore.add(allChunks);
    }


    public String answerUserQuery(String question) {

        List<Document> relevantChunks = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question)
                        .topK(4)
                        .similarityThreshold(0.75)
                        .filterExpression(
                                String.valueOf("category" == "electronics")
                        )
                        .build()
        );

        if (relevantChunks.isEmpty()){
            return "I couldn't found relevant info";
        }

        StringBuilder context = new StringBuilder();

        for(Document document : relevantChunks){

            context.append(document.getText())
                    .append("\n\n");
        }

        String finalContext = context.toString();

        String prompt = """
                You are an AI customer support assistant for our e-commerce company.

                Answer the customer using ONLY the company information provided below.

                If the answer is not available in the provided information, say:
                "I don't have that information in the company documents."
                
                COMPANMY INFORMATION
                %s
                
                """.formatted(finalContext);

        return chatClient.prompt()
                .system(prompt)
                .user(question)
                .call()
                .content();
    }
}