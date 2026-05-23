package org.example.whattsintegration.service;

import org.example.whattsintegration.config.AuthWhatts;
import org.example.whattsintegration.entity.SendMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "zap" ,url = "http://localhost:8080", configuration = org.example.whattsintegration.config.AuthWhatts.class)
public interface WhattsService {

    @PostMapping("/message/sendText/Nando")
    SendMessage sendMessageWhatts(@RequestBody SendMessage message);


}
