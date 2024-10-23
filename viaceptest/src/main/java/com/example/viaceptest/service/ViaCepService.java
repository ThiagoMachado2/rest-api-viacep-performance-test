package com.example.viaceptest.service;

import com.example.viaceptest.model.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {

    private final WebClient webClient;

    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br/ws/").build();
    }

    public Mono<ViaCepResponse> getCepInfo(String cep) {
        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),  // Intercepta erros 4xx e 5xx
                        clientResponse -> Mono.empty()
                )
                .bodyToMono(ViaCepResponse.class)
                .onErrorResume(e -> Mono.empty());
    }
}
