package com.encora.challenge.webclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Value("${api.notification.url}")
    private String notificationApiUrl;

    public WebClient apiNotificationClient() {
        return WebClient.create(notificationApiUrl);
    }

}
