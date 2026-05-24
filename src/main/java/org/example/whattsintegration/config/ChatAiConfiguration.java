package org.example.whattsintegration.config;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.example.whattsintegration.service.AiServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAiConfiguration {

    @Bean
    OllamaChatModel chatModel(){
        return OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
                .modelName("glm-4.7:cloud")
                .build();
    }

    @Bean
    AiServiceConfig aiServiceConfig(OllamaChatModel chatModel){
        return AiServices.builder(AiServiceConfig.class)
                .chatModel(chatModel)
                .build();
    }
}
