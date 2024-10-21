package com.encora.challenge.controller;

import com.encora.challenge.business.NotificationService;
import com.encora.challenge.model.NotificationRequest;
import com.encora.challenge.model.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notification")
    public Mono<NotificationResponse> sendNotification(@RequestBody NotificationRequest notificationRequest) {

        return notificationService.sendNotification(notificationRequest);
    }
}
