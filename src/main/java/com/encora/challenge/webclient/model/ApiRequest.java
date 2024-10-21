package com.encora.challenge.webclient.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApiRequest {

    private String userId;
    private String message;
}
