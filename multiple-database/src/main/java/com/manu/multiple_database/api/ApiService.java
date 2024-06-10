package com.manu.multiple_database.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;


    public ApiService(WebClient.Builder builder){
        this.webClient = builder.baseUrl("http://localhost:8081").build();
    }

    public Mono<CredsModel> getCredentials(String dbName){
        return webClient.get()
                .uri("/getCreds/{dbName}",dbName)
                .header("Authorization","SKJHASKJDHAKJSHDKGDASKJHDKSA")
                .retrieve()
                .bodyToMono(CredsModel.class);
    }

}
