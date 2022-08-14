package com.apis.main.controllers.async.nonblocking;


import com.apis.main.services.LinkService;
import com.apis.main.utils.ParserUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/async/nonblocking/link")
public class AsyncNonBlockingLinkController {

    private final WebClient webClient;

    public AsyncNonBlockingLinkController( WebClient webClient) {
        this.webClient = webClient;
    }


    @GetMapping(value = "/find")
    public Mono<String> findMovieLink(@RequestParam("movie") String movieName){
        Mono<String> linkMono  = webClient
                .get()
                .uri("https://www.watchseries1.video/search/"+movieName)
                .retrieve()
                .bodyToMono(String.class)
                .map(ParserUtil::getLinkFromHtml);

        return linkMono;
    }

    @GetMapping(value = "/findAll")
    public Flux<String> findAllMovieLinks(@RequestParam("movie") String movieName){

        Mono<String> htmlDocMono  = webClient
                .get()
                .uri("https://www.watchseries1.video/search/"+movieName)
                .retrieve()
                .bodyToMono(String.class);


        Flux<String> linksFlux = htmlDocMono.map(ParserUtil::getAllRelevantLinksFromHtml).flatMapMany(Flux::fromIterable);

        return linksFlux;
    }



}
