package com.encora.challenge.business;

import com.encora.challenge.model.NotificationRequest;
import com.encora.challenge.model.NotificationResponse;
import com.encora.challenge.webclient.ApiClient;
import com.encora.challenge.webclient.model.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NotificationServiceImplTest {

    @Mock
    ApiClient apiClient;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendNotification() {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setId("550e8400-e29b-41d4-a716-446655440000");
        apiResponse.setMessage("Enviando mensaje");

        when(apiClient.send(any())).thenReturn(Mono.just(apiResponse));

        Mono<NotificationResponse> result = notificationService.sendNotification(
                NotificationRequest.builder()
                        .id("550e8400-e29b-41d4-a716-446655440000")
                        .message("Enviando mensaje")
                        .userId("1")
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getId().equals("550e8400-e29b-41d4-a716-446655440000") &&
                                response.getMessage().equals("Enviando mensaje")
                )
                .verifyComplete();
    }
}