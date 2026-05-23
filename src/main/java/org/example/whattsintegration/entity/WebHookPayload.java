package org.example.whattsintegration.entity;

import lombok.Data;
@Data
public class WebHookPayload {
    private String event;
    private String instance;
    private MessageData data;

    @Data
    public static class MessageData {
        private MessageKey key;
        private WhatsMessage message;
        private String messageType;
        private String pushName;
    }

    @Data
    public static class MessageKey {
        private String remoteJid;
        private Boolean fromMe;
        private String id;
    }

    @Data
    public static class WhatsMessage {
        private String conversation;
        private ExtendedTextMessage extendedTextMessage;
    }

    @Data
    public static class ExtendedTextMessage {
        private String text;
    }
}


