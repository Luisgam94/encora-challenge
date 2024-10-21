package com.encora.challenge.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NotificationRequest {

    private String userId;
    private LocalDateTime timestamp;
    private String id;
    private String message;
}
