package com.encora.challenge.business;

import com.encora.challenge.model.NotificationRequest;
import com.encora.challenge.model.NotificationResponse;
import com.encora.challenge.webclient.ApiClient;
import com.encora.challenge.webclient.model.ApiRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final ApiClient apiClient;

    @Override
    public Mono<NotificationResponse> sendNotification(NotificationRequest notificationRequest) {
        return apiClient.send(ApiRequest.builder()
                        .userId(notificationRequest.getUserId())
                        .message(notificationRequest.getMessage())
                        .build())
                .map(apiResponse -> NotificationResponse.builder()
                        .id(apiResponse.getId())
                        .message(apiResponse.getMessage())
                        .build())
                .doOnSuccess(s -> log.info("Notification sent successfully"));
    }
}
