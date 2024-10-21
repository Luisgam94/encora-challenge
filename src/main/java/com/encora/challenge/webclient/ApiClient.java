package com.encora.challenge.webclient;

import com.encora.challenge.webclient.model.ApiRequest;
import com.encora.challenge.webclient.model.ApiResponse;
import reactor.core.publisher.Mono;

public interface ApiClient {

    Mono<ApiResponse> send(ApiRequest apiRequest);
}
