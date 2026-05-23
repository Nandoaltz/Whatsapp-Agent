package org.example.whattsintegration.entity;

import lombok.Builder;

@Builder
public record SendMessage(
        String number,
        String text
) {
}
