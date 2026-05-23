package org.example.whattsintegration.controller;

import lombok.RequiredArgsConstructor;
import org.example.whattsintegration.entity.SendMessage;
import org.example.whattsintegration.entity.WebHookPayload;
import org.example.whattsintegration.service.AiServiceConfig;
import org.example.whattsintegration.service.WhattsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WhattsController {

    private final WhattsService whattsController;
    private final AiServiceConfig aiServiceConfig;

    @GetMapping
    public String olaMundo() {
        return "Ola mundo";
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody SendMessage message) {
        whattsController.sendMessageWhatts(message);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/chat")
    public ResponseEntity<Void> receberMensagem(@RequestBody WebHookPayload payload) {

        System.out.println("Payload recebido: " + payload);

        if (payload.getEvent() == null || !"messages.upsert".equals(payload.getEvent())) {
            return ResponseEntity.ok().build();
        }

        WebHookPayload.MessageData data = payload.getData();
        if (data == null || data.getKey() == null || data.getMessage() == null) {
            System.out.println("AVISO: data, key ou message veio nulo");
            return ResponseEntity.ok().build();
        }

        if (Boolean.TRUE.equals(data.getKey().getFromMe())) {
            return ResponseEntity.ok().build();
        }

        String numero = data.getKey().getRemoteJid();
        String texto = extrairTexto(data.getMessage());

        System.out.println("Número: " + numero);
        System.out.println("Mensagem: " + texto);

        if (texto == null || texto.isBlank()) {
            System.out.println("AVISO: texto nulo ou vazio");
            return ResponseEntity.ok().build();
        }

        System.out.println("Chamando Ollama...");
        String chat = aiServiceConfig.chat(texto);
        System.out.println("Resposta Ollama: " + chat);

        SendMessage build = SendMessage.builder()
                .number(numero)
                .text(chat)
                .build();

        whattsController.sendMessageWhatts(build);
        System.out.println("Mensagem enviada!");

        return ResponseEntity.ok().build();
    }

    private String extrairTexto(WebHookPayload.WhatsMessage message) {
        if (message.getConversation() != null) {
            return message.getConversation();
        }
        if (message.getExtendedTextMessage() != null) {
            return message.getExtendedTextMessage().getText();
        }
        return null;
    }
}