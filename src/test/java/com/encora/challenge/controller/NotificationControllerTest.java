package com.encora.challenge.controller;

import com.encora.challenge.business.NotificationService;
import com.encora.challenge.model.NotificationRequest;
import com.encora.challenge.model.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NotificationService notificationService;

    @Test
    void sendNotification() {

        NotificationRequest mockRequest = NotificationRequest.builder()
                .id("550e8400-e29b-41d4-a716-446655440000")
                .message("Enviando notificacion")
                .userId("1")
                .timestamp(LocalDateTime.now())
                .build();

        when(notificationService.sendNotification(mockRequest))
                .thenReturn(Mono.just(NotificationResponse
                        .builder()
                                .id("550e8400-e29b-41d4-a716-446655440000")
                                .message("Enviando notificacion")
                        .build()));

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/notification")
                        .build())
                .bodyValue(mockRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NotificationResponse.class);

    }
}