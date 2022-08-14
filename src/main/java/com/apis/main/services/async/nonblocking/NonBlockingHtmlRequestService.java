package com.apis.main.services.async.nonblocking;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class NonBlockingHtmlRequestService {

    private final String SEARCH_URL = "https://www.watchseries1.video/search/";
    private WebClient webClient;

    public NonBlockingHtmlRequestService(){
        webClient = WebClient.create();
    }

    public Mono<String> getHtmlResult(String movieName){

        Mono<String> stringMono  = webClient
                .get()
                .uri(SEARCH_URL+movieName)
                .retrieve()
                .bodyToMono(String.class);

        return stringMono;
    }



}
