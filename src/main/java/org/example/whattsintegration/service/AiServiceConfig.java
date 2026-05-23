package org.example.whattsintegration.service;

import dev.langchain4j.service.SystemMessage;

public interface AiServiceConfig {

    @SystemMessage("""
            Voce é um agente de whattsapp
            """)
    String chat(String message);

}
