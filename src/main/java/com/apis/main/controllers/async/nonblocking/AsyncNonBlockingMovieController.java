package com.apis.main.controllers.async.nonblocking;

import com.apis.main.entities.Movie;
import com.apis.main.services.MovieService;
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
@RequestMapping("/async/nonblocking/movie")
public class AsyncNonBlockingMovieController {

    private final WebClient webClient;

    public AsyncNonBlockingMovieController(WebClient webClient) {
        this.webClient = webClient;
    }


    @GetMapping(value = "/find")
    public Mono<Movie> findMovieLink(@RequestParam("movie") String movieName){

        Mono<String> htmlDocMono  = webClient
                .get()
                .uri("https://www.watchseries1.video/search/"+movieName)
                .retrieve()
                .bodyToMono(String.class);

        Mono<Movie> movieMono = htmlDocMono.map(ParserUtil::getMovieFromHtmlDocument);

        return movieMono;
    }

    @GetMapping(value = "/findAll")
    public Flux<Movie> findAllMovieLinks(@RequestParam("movie") String movieName){


        Mono<String> htmlDocMono  = webClient
                .get()
                .uri("https://www.watchseries1.video/search/"+movieName)
                .retrieve()
                .bodyToMono(String.class);


        Flux<Movie> moviesFlux = htmlDocMono.map(ParserUtil::getAllMovies).flatMapMany(Flux::fromIterable);

        return moviesFlux;
    }

}
