package com.encora.challenge.webclient;

import com.encora.challenge.exceptions.CustomizedMessageException;
import com.encora.challenge.webclient.config.WebClientConfig;
import com.encora.challenge.webclient.model.ApiRequest;
import com.encora.challenge.webclient.model.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiClientImpl implements ApiClient {

    private final WebClientConfig apiClient;

    @Override
    public Mono<ApiResponse> send(ApiRequest apiRequest) {
        return apiClient.apiNotificationClient()
                .post()
                .uri("/notifications/mock")
                .bodyValue(apiRequest)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody ->
                                        Mono.error(new CustomizedMessageException(extractErrorMessage(errorBody))))
                )
                .bodyToMono(ApiResponse.class)
                .doOnError(error -> log.error("Error sending request: ", error));
    }

    private String extractErrorMessage(String errorBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(errorBody);
            return rootNode.path("error").path("message").asText("Client error");
        } catch (JsonProcessingException e) {
            log.error("Error parsing error response body: ", e);
            return "Client error";
        }
    }
}
