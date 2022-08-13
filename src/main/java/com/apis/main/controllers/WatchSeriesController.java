package com.apis.main.controllers;

import com.apis.main.services.ParserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WatchSeriesController {

    private final RestTemplate restTemplate;
    private final ParserService parserService;

    public WatchSeriesController(RestTemplate restTemplate, ParserService parserService) {
        this.restTemplate = restTemplate;
        this.parserService = parserService;
    }


    @GetMapping("/first")
    public String firstFunction(){
        return "something first";
    }

    @GetMapping("/search")
    public ResponseEntity<String> findMovieLink(@RequestParam("movie") String movie){
        String url = "https://www.watchseries1.video/search/"+movie;
        String htmlResult = restTemplate.getForObject(url, String.class);

        String link = parserService.getLink(htmlResult);

        if (link.isEmpty()){
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(link, HttpStatus.OK);
    }



}
