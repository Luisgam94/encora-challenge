package com.encora.challenge.business;

import com.encora.challenge.model.NotificationRequest;
import com.encora.challenge.model.NotificationResponse;
import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<NotificationResponse> sendNotification(NotificationRequest notificationRequest);

}
